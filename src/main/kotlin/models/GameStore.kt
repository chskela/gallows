package models

class GameStore(private var _gameState: GameState = GameState.Menu()) {

    fun setGameState(gameState: GameState) {
        _gameState = gameState
    }

    fun getGameState()= _gameState

}