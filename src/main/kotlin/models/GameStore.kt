package models

class GameStore(private var _gameState: GameState = GameState.Menu(), private val maxNumberOfErrors: Int) {

    fun setGameState(gameState: GameState) {
        _gameState = gameState
    }

    fun getGameState()= _gameState

    fun handlerEnteredLetter(letter: Char) {
        when (val state = _gameState) {

            is GameState.Process -> {
                val newState = if (state.word.contains(letter)) {
                    state.copy(mask = updateMask(state, letter))
                } else {
                    state.copy(attempts = state.attempts + 1)
                }
                checkGameState(newState)
            }

            else -> _gameState
        }
    }

    private fun checkGameState(newState: GameState.Process) {
        _gameState = when {
            isWin(newState) -> GameState.Win(word = newState.word)

            isGameOver(newState) -> GameState.GameOver(word = newState.word)

            else -> newState
        }
    }

    private fun isWin(state: GameState.Process) = state.mask == state.word

    private fun isGameOver(state: GameState.Process) = state.attempts >= maxNumberOfErrors

    private fun updateMask(state: GameState.Process, letter: Char) = state.mask.mapIndexed { index, c ->
        if (letter == state.word[index]) letter else c
    }.joinToString("")
}