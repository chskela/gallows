import controllers.GameController
import models.GameStore
import views.GameDisplay

fun main() {

    val game = GameController(gameStore = GameStore(), gameDisplay = GameDisplay())
    game.loop()
}