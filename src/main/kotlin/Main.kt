import Settings.MAX_NUMBER_OF_ERRORS
import Settings.PATH_FILE
import models.GameStore
import models.WordStore
import presenters.GamePresenter
import views.ConsoleDisplay

fun main() {

    val game = GamePresenter(
        wordStore = WordStore(pathFile = PATH_FILE),
        gameStore = GameStore(maxNumberOfErrors = MAX_NUMBER_OF_ERRORS),
        gameDisplay = ConsoleDisplay()
    )

    game.loop()
}