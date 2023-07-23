class Game(private var state: State = State()) {

    private var isGamePlay = true

    fun loop() {
        while (isGamePlay) {
            showGameState()
            handlerUserInput()
            isGamePlay = checkGame()
        }
    }

    private fun checkGame() = isNotGameOver() && isNotWin()

    private fun isNotWin() = state.mask != state.word

    private fun isNotGameOver() = state.attempts < Settings.NUMBER_OF_MISTAKES

    private fun showGameState() {
        print(
            """
            Загаданное слово: ${state.mask}
            Ошибок: ${state.attempts}
            Виселица: ${state.gallows}
            Введите букву: 
        """.trimIndent()
        )
    }

    private fun handlerUserInput() {
        val input = readln().trim().lowercase()

        if (validateInput(input)) {
            println("Буква не распознана! Пожалуйста, попробуйте еще раз")
            return
        }

        val letter = input.first()

        state = if (state.word.contains(letter)) {
            val newMask = state.mask.mapIndexed { index, c ->
                if (letter == state.word[index]) letter else c
            }.joinToString("")

            state.copy(mask = newMask)
        } else {
            state.copy(attempts = state.attempts + 1)
        }
    }

    private fun validateInput(input: String): Boolean = input.isBlank() || !input.first().isLetter()
}
