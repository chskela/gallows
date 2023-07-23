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
        val input = readlnOrNull()?.trim()?.lowercase()

        if (input?.length != 1) {
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
}
