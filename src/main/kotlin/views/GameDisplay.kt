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
        println("Досвидание!!!")
    }

    fun showErrorInput() {
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
}