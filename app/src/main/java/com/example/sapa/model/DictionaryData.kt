package com.example.sapa.model

object DictionaryData {
    val baseUrl = "https://storage.googleapis.com/sapa-apps-capstone/dictionary/"

    // List of letters from a to z
    val letters = ('a'..'z')

    // Generate the list of complete URLs
    val dictionary = letters.map { letter -> "$baseUrl$letter.png" }

    fun generateSignLanguageAlphabetQuestions(): List<StageDetail> {

        // Pilih 5 huruf acak dari letters
        val selectedLetters = letters.shuffled().take(5)

        // Buat StageDetail untuk setiap huruf yang dipilih
        val signLanguageAlphabetQuestions = selectedLetters.mapIndexed { index, letter ->
            val options = letters.shuffled().take(3).toMutableList().apply {
                add(letter)
                shuffle()
            }

            StageDetail(
                question_id = index + 1,
                Image = "$baseUrl$letter.png",
                option1 = options[0].uppercaseChar().toString(),
                option2 = options[1].uppercaseChar().toString(),
                option3 = options[2].uppercaseChar().toString(),
                option4 = options[3].uppercaseChar().toString(),
                answer = letter.uppercaseChar().toString()
            )
        }

        return signLanguageAlphabetQuestions
    }
}