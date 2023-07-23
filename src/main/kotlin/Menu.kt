class Menu {

    private var isExit: Boolean = false

    private fun showMenu() {
        println(
            """
            Здравствуйте, это игра "Виселица",
            если хотите сыграть введите "Y",
            для выхода введите "N"
        """.trimIndent()
        )
    }

    fun loop() {
        do {
            showMenu()

            when (readlnOrNull()) {
                "Y" -> TODO()

                "N" -> {
                    isExit = true
                }

                else -> {
                    println("Команда не распознана! Пожалуйста, попробуйте еще раз")
                }
            }
        } while (!isExit)
    }
}