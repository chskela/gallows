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

    fun showGameState(mask: String, attempts: Int, gallows: String) {
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

    fun showGameOver(word: String, gallows: String){
        println("""
            
            К сожалению, Вы проиграли!!!
            Виселица: $gallows
            Загаданное слово: ${word.uppercase()}
            
        """.trimIndent())
    }
}