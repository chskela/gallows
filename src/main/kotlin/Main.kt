import Settings.MAX_NUMBER_OF_ERRORS
import Settings.PATH_FILE
import presenters.GamePresenter
import models.GameStore
import models.WordStore
import views.GameDisplay

fun main() {

    val game = GamePresenter(
        wordStore = WordStore(pathFile = PATH_FILE),
        gameStore = GameStore(),
        gameDisplay = GameDisplay(),
        maxNumberOfErrors = MAX_NUMBER_OF_ERRORS
    )

    game.loop()
}