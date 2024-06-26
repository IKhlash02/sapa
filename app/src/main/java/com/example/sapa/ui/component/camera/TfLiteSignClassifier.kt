package com.example.sapa.ui.component.camera

import android.content.Context
import android.graphics.Bitmap
import android.view.Surface
import com.example.sapa.model.Classification
import org.tensorflow.lite.support.image.ImageProcessor
import org.tensorflow.lite.support.image.TensorImage
import org.tensorflow.lite.task.core.BaseOptions
import org.tensorflow.lite.task.core.vision.ImageProcessingOptions
import org.tensorflow.lite.task.vision.classifier.ImageClassifier

class TfLiteSignClassifier(
    private val context: Context,
    private val threshold: Float = 0.5f,
    private val maxResult: Int = 1

) : SignClassifier {

    private var classifier: ImageClassifier? = null

    private fun setupClassifier(){
        val baseOptions = BaseOptions.builder()
            .setNumThreads(2)
            .build()

        val options = ImageClassifier.ImageClassifierOptions.builder()
            .setBaseOptions(baseOptions)
            .setMaxResults(maxResult)
            .setScoreThreshold(threshold)
            .build()

        try {
            classifier = ImageClassifier.createFromFileAndOptions(
                context,
                "model1_3.tflite",
                options
            )
        } catch (e: IllegalStateException){
            e.printStackTrace()
        }
    }


    override fun classify(bitmap: Bitmap, rotation: Int): List<Classification> {
        if (classifier ==  null){
            setupClassifier()
        }

        val imageProcessor = ImageProcessor.Builder().build()
        val tensorImage = imageProcessor.process(TensorImage.fromBitmap(bitmap))

        val imageProcessingOptions = ImageProcessingOptions.builder()
            .setOrientation(getOriantationFromRotation(rotation))
            .build()

        val results = classifier?.classify(tensorImage, imageProcessingOptions)

        return  results?.flatMap { classification ->
            classification.categories.map { category ->
                Classification(
                    name = category.label,
                    score = category.score
                )
            }
        }?.distinctBy { it.name } ?: emptyList()
    }

    private  fun getOriantationFromRotation(rotation: Int) : ImageProcessingOptions.Orientation{
        return when(rotation){
            Surface.ROTATION_0 -> ImageProcessingOptions.Orientation.RIGHT_TOP
            Surface.ROTATION_90 -> ImageProcessingOptions.Orientation.TOP_LEFT
            Surface.ROTATION_180 -> ImageProcessingOptions.Orientation.TOP_RIGHT
            else -> ImageProcessingOptions.Orientation.BOTTOM_RIGHT
        }
    }
}