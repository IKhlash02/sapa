package com.example.sapa.ui.component.camera


import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.example.sapa.model.Classification

class SignImageAnalyzer(
    private  val classifier: SignClassifier,
    private val onResults: (List<Classification>) -> Unit
): ImageAnalysis.Analyzer {

    private var frameSkipCounter = 0

    override fun analyze(image: ImageProxy) {
        if (frameSkipCounter %30 == 0){
            val rotationDegrees = image.imageInfo.rotationDegrees
            val bitmap = image
                .toBitmap()
//                .centerCrop(28, 28)

            val result  = classifier.classify(bitmap, rotationDegrees)
            onResults(result)


        }

        frameSkipCounter++
        image.close()
    }
}
