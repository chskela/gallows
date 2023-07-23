data class State(
    val word: String = "",
    val mask: String = "*".repeat(word.length),
    val attempts: Int = 0,
    val gallows: String = ""
)
