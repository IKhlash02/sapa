package com.example.sapa.ui.component.camerax

import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy

class SignClassifierAnalyzer(
    private val classifier: SignClassifier,
    private val onResults: (List<Classification>) -> Unit
): ImageAnalysis.Analyzer{

    private var frameSkipCounter = 0


    override fun analyze(image: ImageProxy) {
        if (frameSkipCounter% 30 == 0){

            val rotationDegress = image.imageInfo.rotationDegrees
            val bitmap = image.toBitmap()
                .centerCrop(321, 321)

            val results = classifier.classify(bitmap, rotationDegress)
            onResults(results)
            image.close()
        }

        frameSkipCounter++

    }


}