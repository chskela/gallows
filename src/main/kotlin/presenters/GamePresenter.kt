package presenters

import models.GameState
import models.GameStore
import models.WordStore
import views.GameDisplay

class GamePresenter(
    private val wordStore: WordStore,
    private var gameStore: GameStore,
    private val gameDisplay: GameDisplay
) {

    private var isPlayGame = true

    fun loop() {
        do {
            when (val gameState = gameStore.getGameState()) {
                is GameState.Menu -> {
                    var isExitMenu: Boolean
                    gameDisplay.showMenu()

                    do {
                        when (gameDisplay.userInput()) {
                            "y" -> {
                                gameDisplay.showLetsPlay()
                                gameStore.setGameState(gameState = GameState.Process(word = wordStore.getRandomWord()))
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
                    gameDisplay.showGameState(
                        mask = gameState.mask,
                        attempts = gameState.attempts,
                        usedLetters = gameState.usedLetters
                    )
                    val input = gameDisplay.userInput()

                    if (validateInput(input)) {
                        val letter = input.first()

                        if (gameStore.checkUsedLetters(letter)) {
                            gameDisplay.showUsedLetter(letter)
                        } else {
                            gameStore.handlerEnteredLetter(letter)
                        }

                    } else {
                        gameDisplay.showErrorInputLetter()
                    }
                }

                is GameState.Win -> {
                    gameDisplay.showWin(word = gameState.word)
                    gameStore.setGameState(GameState.Menu())
                }

                is GameState.GameOver -> {
                    gameDisplay.showGameOver(word = gameState.word)
                    gameStore.setGameState(GameState.Menu())
                }
            }

        } while (isPlayGame)
    }

    private fun validateInput(input: String): Boolean = input.length == 1 && input.first().isLetter()
}
