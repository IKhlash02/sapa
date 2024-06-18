package com.example.sapa.model

object DictionaryData {
    val baseUrl = "https://storage.googleapis.com/sapa-apps-capstone/dictionary/"

    // List of letters from a to z
    val letters = ('a'..'z')
    val numbers = (1..10).toList()

    val combined = letters + numbers.map { it.toString() }

    // Generate the list of complete URLs
    val dictionary = combined.map { letter -> "$baseUrl$letter.png" }
}