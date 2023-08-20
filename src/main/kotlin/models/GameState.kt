package models

sealed class GameState {
    data class Menu(var isExit: Boolean = false) : GameState()
    data class Process(
        val word: String,
        val mask: String = word.replace(Regex("[А-яЁё]"), "*"),
        val attempts: Int = 0,
        val usedLetters: Set<Char> = emptySet()
    ) : GameState()

    data class Win(val word: String) : GameState()

    data class GameOver(val word: String)  : GameState()
}