package views

class ConsoleDisplay : GameDisplay {

    override fun showMenu() {
        print(
            """
            Здравствуйте, это игра "Виселица",
            если хотите сыграть введите "Y",
            для выхода введите "N"
            Ввод: 
        """.trimIndent()
        )
    }

    override fun showLetsPlay() {
        println("Сыграем!!!")
    }

    override fun showGoodbye() {
        println("До свидания!!!")
    }

    override fun showErrorInputCommand() {
        println("Команда не распознана! Пожалуйста, попробуйте еще раз")
    }

    override fun showGameState(mask: String, attempts: Int) {
        val gallows = getNewGallows(attempts)

        print(
            """
            Загаданное слово: ${mask.uppercase().split("").joinToString(" ")}
            Ошибок: $attempts
            Виселица: $gallows
            Введите букву: 
        """.trimIndent()
        )
    }

    override fun showErrorInputLetter() {
        println("Буква не распознана! Пожалуйста, попробуйте еще раз")
    }

    override fun showWin(word: String){
        println("""
            
            Поздравляю, Вы победили!!!
            Загаданное слово: ${word.uppercase()}
            
        """.trimIndent())
    }

    override fun showGameOver(word: String){
        println("""
            
            К сожалению, Вы проиграли!!!
            Виселица: ${Gallows.RightLeg.view}
            Загаданное слово: ${word.uppercase()}
            
        """.trimIndent())
    }

    override fun userInput(): String = readln().trim().lowercase()

    private fun getNewGallows(attempts: Int) = when (attempts) {
        1 -> Gallows.Had
        2 -> Gallows.Torso
        3 -> Gallows.LeftHand
        4 -> Gallows.RightHand
        5 -> Gallows.LeftLeg
        6 -> Gallows.RightLeg
        else -> Gallows.Default
    }.view
}