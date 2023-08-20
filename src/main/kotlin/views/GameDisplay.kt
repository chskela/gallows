package views

interface GameDisplay {
    fun showMenu()
    fun showLetsPlay()
    fun showGoodbye()
    fun showErrorInputCommand()
    fun showGameState(mask: String, attempts: Int)
    fun showErrorInputLetter()
    fun showWin(word: String)
    fun showGameOver(word: String)
    fun userInput(): String
    fun showUsedLetter(letter: Char)
}