package controllers

import Settings
import models.GameState
import models.GameStore
import views.Gallows
import views.GameDisplay

class GameController(private var gameStore: GameStore, private val gameDisplay: GameDisplay) {

    private var isPlayGame = true

    fun loop() {
        do {
            when (val gameState = gameStore.getGameState()) {
                is GameState.Menu -> {
                    var isExitMenu: Boolean
                    gameDisplay.showMenu()

                    do {
                        when (readlnOrNull()?.trim()?.lowercase()) {
                            "y" -> {
                                gameDisplay.showLetsPlay()
                                gameStore.setGameState(gameState = GameState.Process())
                                isExitMenu = true
                            }

                            "n" -> {
                                gameDisplay.showGoodbye()
                                isExitMenu = true
                                isPlayGame = false
                            }

                            else -> {
                                gameDisplay.showErrorInputCommand()
                                isExitMenu = false
                            }
                        }
                    } while (!isExitMenu)
                }

                is GameState.Process -> {
                    val mask = gameState.mask
                    val attempts = gameState.attempts
                    val gallows = gameState.gallows.view
                    gameDisplay.showGameState(mask = mask, attempts = attempts, gallows = gallows)

                    val newState = handlerUserInput(state = gameState)

                    if (isWin(newState)) {
                        gameStore.setGameState(GameState.Win(newState.word))
                    } else if (isGameOver(newState)) {
                        gameStore.setGameState(GameState.GameOver(newState.word))
                    } else {
                        gameStore.setGameState(gameState = newState)
                    }
                }

                is GameState.Win -> {
                    gameDisplay.showWin(gameState.word)
                    gameStore.setGameState(GameState.Menu())
                }

                is GameState.GameOver -> {
                    gameDisplay.showGameOver(gameState.word)
                    gameStore.setGameState(GameState.Menu())
                }
            }

        } while (isPlayGame)
    }

    private fun isWin(state: GameState.Process) = state.mask == state.word

    private fun isGameOver(state: GameState.Process) = state.attempts >= Settings.NUMBER_OF_MISTAKES

    private fun handlerUserInput(state: GameState.Process): GameState.Process {
        val input = readln().trim().lowercase()
        if (validateInput(input)) {
            gameDisplay.showErrorInputLetter()
            return state
        }

        val letter = input.first()
        val n = if (state.word.contains(letter)) {
            val newMask = getNewMask(state, letter)

            state.copy(mask = newMask)
        } else {
            val newAttempts = state.attempts + 1
            val newGallows = getNewGallows(newAttempts)

            state.copy(attempts = newAttempts, gallows = newGallows)
        }
        return n
    }

    private fun getNewMask(state: GameState.Process, letter: Char) = state.mask.mapIndexed { index, c ->
        if (letter == state.word[index]) letter else c
    }.joinToString("")

    private fun getNewGallows(attempts: Int) = when (attempts) {
        1 -> Gallows.Had
        2 -> Gallows.Torso
        3 -> Gallows.LeftHand
        4 -> Gallows.RightHand
        5 -> Gallows.LeftLeg
        6 -> Gallows.RightLeg
        else -> Gallows.Default
    }

    private fun validateInput(input: String): Boolean =
        input.isBlank() || input.length != 1 || !input.first().isLetter()
}
