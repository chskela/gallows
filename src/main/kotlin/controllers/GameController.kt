package controllers

import Settings
import models.GameState
import models.GameStore
import views.Gallows
import views.GameDisplay

class GameController(
    private var gameStore: GameStore = GameStore(),
    private val gameDisplay: GameDisplay = GameDisplay()
) {


    private var isPlayGame = true

    fun loop() {
        println("<============================>")

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
                                gameDisplay.showErrorInput()
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
                        gameStore.setGameState(GameState.Win)
                    } else if (isGameOver(newState)) {
                        gameStore.setGameState(GameState.GameOver)
                    } else {
                        gameStore.setGameState(gameState = newState)
                    }
                }

                GameState.Win -> {
                    println("Вы выиграли")
                    gameStore.setGameState(GameState.Menu())
                }

                GameState.GameOver -> {
                    println("Вы проиграли")
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
            println("Буква не распознана! Пожалуйста, попробуйте еще раз")
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

    private fun validateInput(input: String): Boolean = input.isBlank() || !input.first().isLetter()
}
