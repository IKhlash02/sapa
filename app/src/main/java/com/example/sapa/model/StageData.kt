package com.example.sapa.model

object UnitData {
    val units = listOf(
        // Level Dasar
        Unit(
            id = 1,
            namaTopik = "Abjad A-N",
            unitNo = 1,
            stages = listOf(
                Stage(id = 1, noStage = 1),  // A - D (PG)
                Stage(id = 2, noStage = 2),  // E - I (PG)
                Stage(id = 3, noStage = 3),  // J - N (PG)
                Stage(id = 4, noStage = 4)   // Kuis A - N (VIDEO)
            )
        ),
        Unit(
            id = 2,
            namaTopik = "Abjad O - Z",
            unitNo = 2,
            stages = listOf(
                Stage(id = 5, noStage = 1),  // O - R (PG)
                Stage(id = 6, noStage = 2),  // S - V (PG)
                Stage(id = 7, noStage = 3),  // W - Z (PG)
                Stage(id = 8, noStage = 4)   // Kuis O - Z (VIDEO)
            )
        ),
        Unit(
            id = 3,
            namaTopik = "Angka 0 - 9",
            unitNo = 3,
            stages = listOf(
                Stage(id = 9, noStage = 1),  // 0 - 4
                Stage(id = 10, noStage = 2), // 5 - 9
                Stage(id = 11, noStage = 3)  // Kuis 0 - 9
            )
        ),

        // Level Menengah
        Unit(
            id = 4,
            namaTopik = "Kata ganti orang",
            unitNo = 4,
            stages = listOf(
                Stage(id = 12, noStage = 1), // aku, saya
                Stage(id = 13, noStage = 2), // kamu, dia
                Stage(id = 14, noStage = 3)  // Kuis semuanya
            )
        ),
        Unit(
            id = 5,
            namaTopik = "Bertanya?",
            unitNo = 5,
            stages = listOf(
                Stage(id = 15, noStage = 1), // Apa, siapa, kenapa
                Stage(id = 16, noStage = 2), // kapan, di mana, bagaimana
                Stage(id = 17, noStage = 3)  // Kuis semuanya
            )
        )
    )


}