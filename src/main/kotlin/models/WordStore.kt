package models

import java.io.File

class WordStore(pathFile: String) {

    private var listOfWords: List<String> = try {
        File(pathFile).readText().split("\n").map { str -> str.trim() }
    } catch (e: Exception) {
        listOf("Ошибка")
    }

    fun getRandomWord() = listOfWords.random()
}