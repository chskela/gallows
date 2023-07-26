package models

import views.Gallows

sealed class GameState {
    data class Menu(var isExit: Boolean = false) : GameState()
    data class Process(
        val word: String = "мама",
        val mask: String = "*".repeat(word.length),
        val attempts: Int = 0,
        val gallows: Gallows = Gallows.Default
    ) : GameState()

    object Win : GameState()

    object GameOver : GameState()
}