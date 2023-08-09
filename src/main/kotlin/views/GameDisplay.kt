package views

class GameDisplay {

    fun showMenu() {
        print(
            """
            Здравствуйте, это игра "Виселица",
            если хотите сыграть введите "Y",
            для выхода введите "N"
            Ввод: 
        """.trimIndent()
        )
    }

    fun showLetsPlay() {
        println("Сыграем!!!")
    }

    fun showGoodbye() {
        println("До свидания!!!")
    }

    fun showErrorInputCommand() {
        println("Команда не распознана! Пожалуйста, попробуйте еще раз")
    }

    fun showGameState(mask: String, attempts: Int) {
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

    fun showErrorInputLetter() {
        println("Буква не распознана! Пожалуйста, попробуйте еще раз")
    }

    fun showWin(word: String){
        println("""
            
            Поздравляю, Вы победили!!!
            Загаданное слово: ${word.uppercase()}
            
        """.trimIndent())
    }

    fun showGameOver(word: String){
        println("""
            
            К сожалению, Вы проиграли!!!
            Виселица: ${Gallows.RightLeg.view}
            Загаданное слово: ${word.uppercase()}
            
        """.trimIndent())
    }

    private fun getNewGallows(attempts: Int) = when (attempts) {
        1 -> Gallows.Had
        2 -> Gallows.Torso
        3 -> Gallows.LeftHand
        4 -> Gallows.RightHand
        5 -> Gallows.LeftLeg
        6 -> Gallows.RightLeg
        else -> Gallows.Default
    }
}