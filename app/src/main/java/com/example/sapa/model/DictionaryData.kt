package com.example.sapa.model

object DictionaryData {
    val baseUrl = "https://storage.googleapis.com/sapa-apps-capstone/dictionary/"

    // List of letters from a to z
    val letters = ('a'..'z')
    val numbers = (1..10).toList()


    // Generate the list of complete URLs
    val dictionaryLetters = letters.map { letter -> "$baseUrl$letter.png" }
    val dictionaryNumbers = numbers.map { number -> "$baseUrl$number.png" }
}