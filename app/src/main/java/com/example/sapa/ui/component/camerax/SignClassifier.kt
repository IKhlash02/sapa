package com.example.sapa.ui.component.camerax

import android.graphics.Bitmap
import com.example.sapa.ui.component.camerax.Classification

interface SignClassifier {
    fun classify(bitmap: Bitmap, rotation: Int): List<Classification>
}