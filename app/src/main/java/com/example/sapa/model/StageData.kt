package com.example.sapa.model

object UnitData {
    val units = listOf(
        // Level Dasar
        Unit(
            id = 1,
            namaTopik = "Abjad A-N",
            unitNo = 1,
            stages = listOf(
                Stage(id = 1, idDetail = 1),  // A - D (PG)
                Stage(id = 2, idDetail = 2),  // E - I (PG)
                Stage(id = 3, idDetail = 3),  // J - N (PG)
                Stage(id = 4, isExam = true,  idDetail = 1)  // Kuis A - N (VIDEO)
            )
        ),
        Unit(
            id = 2,
            namaTopik = "Abjad O - Z",
            unitNo = 2,
            stages = listOf(
                Stage(id = 5,  idDetail = 4),  // O - R (PG)
                Stage(id = 6, idDetail = 5),  // S - V (PG)
                Stage(id = 7, idDetail = 6),  // W - Z (PG)
                Stage(id = 8, isExam = true,  idDetail = 2)   // Kuis O - Z (VIDEO)
            )
        ),
        Unit(
            id = 3,
            namaTopik = "Angka 0 - 9",
            unitNo = 3,
            stages = listOf(
                Stage(id = 9, idDetail = 7),  // 0 - 4
                Stage(id = 10, idDetail = 8),
                Stage(id = 11, idDetail = 9),// 5 - 9
                Stage(id = 12, isExam = true, idDetail = 3)  // Kuis 0 - 9
            )
        ),

        )
}