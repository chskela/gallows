data class State(
    val word: String = "мама",
    val mask: String = "*".repeat(word.length),
    val attempts: Int = 0,
    val gallows: String = ""
)
