package com.example.sapa.ui.component.camera

import android.graphics.Bitmap
import com.example.sapa.model.Classification


interface SignClassifier {

    fun classify(bitmap: Bitmap, rotation: Int) : List<Classification>
}