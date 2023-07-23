class Menu {

    private var isExit: Boolean = false

    private fun showMenu() {
        print(
            """
            Здравствуйте, это игра "Виселица",
            если хотите сыграть введите "Y",
            для выхода введите "N"
            Ввод: 
        """.trimIndent()
        )
    }

    fun loop() {
        showMenu()
        do {
            when (readlnOrNull()?.trim()?.lowercase()) {
                "y" -> TODO()

                "n" -> {
                    println("Досвидание!!!")
                    isExit = true
                }

                else -> {
                    println("Команда не распознана! Пожалуйста, попробуйте еще раз")
                }
            }
        } while (!isExit)
    }
}