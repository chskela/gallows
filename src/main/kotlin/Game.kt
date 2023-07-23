class Game(private var state: State = State()) {

    private var isGamePlay = true

    fun loop() {
        println("<============================>")
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
            Загаданное слово: ${state.mask.uppercase().split("").joinToString(" ")}
            Ошибок: ${state.attempts}
            Виселица: ${state.gallows.view}
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
            val newMask = getNewMask(letter)

            state.copy(mask = newMask)
        } else {
            val newAttempts = state.attempts + 1
            val newGallows = getNewGallows(newAttempts)

            state.copy(attempts = newAttempts, gallows = newGallows)
        }
    }

    private fun getNewMask(letter: Char) = state.mask.mapIndexed { index, c ->
        if (letter == state.word[index]) letter else c
    }.joinToString("")

    private fun getNewGallows(attempts: Int) = when (attempts) {
        1 -> Gallows.Had
        2 -> Gallows.Torso
        3 -> Gallows.LeftHand
        4 -> Gallows.RightHand
        5 -> Gallows.LeftLeg
        6 -> Gallows.RightLeg
        else -> Gallows.Default
    }

    private fun validateInput(input: String): Boolean = input.isBlank() || !input.first().isLetter()
}
