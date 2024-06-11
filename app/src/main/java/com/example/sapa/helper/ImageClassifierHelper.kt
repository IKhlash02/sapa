package com.example.sapa.helper

import android.content.Context
import android.graphics.Bitmap
import android.os.SystemClock
import android.util.Log
import androidx.camera.core.ImageProxy
import com.google.mediapipe.framework.image.BitmapImageBuilder
import com.google.mediapipe.tasks.components.containers.Classifications
import com.google.mediapipe.tasks.core.BaseOptions
import com.google.mediapipe.tasks.vision.core.ImageProcessingOptions
import com.google.mediapipe.tasks.vision.core.RunningMode
import com.google.mediapipe.tasks.vision.imageclassifier.ImageClassifier

class ImageClassifierHelper(
    private var threshold: Float = 0.5f,
    private var maxResults: Int = 3,
    private val modelName: String = "model1.tflite",
    val context: Context,
    private val runningMode: RunningMode = RunningMode.LIVE_STREAM,
    val classifierListener: ClassifierListener?
) {
    private var imageClassifier: ImageClassifier? = null

    init {
        setupImageClassifier()
    }

    private fun setupImageClassifier() {
        val optionsBuilder = ImageClassifier.ImageClassifierOptions.builder()
            .setScoreThreshold(threshold)
            .setMaxResults(maxResults)
            .setRunningMode(runningMode)

        if (runningMode == RunningMode.LIVE_STREAM) {
            optionsBuilder.setResultListener { result, image ->
                val finishTimeMs = SystemClock.uptimeMillis()
                val inferenceTime = finishTimeMs - result.timestampMs()
                classifierListener?.onResults(
                    result.classificationResult().classifications(),
                    inferenceTime
                )
            }.setErrorListener { error ->
                classifierListener?.onError(error.message.toString())
            }
        }


        val baseOptionsBuilder = BaseOptions.builder()
            .setModelAssetPath(modelName)
        optionsBuilder.setBaseOptions(baseOptionsBuilder.build())

        try {
            imageClassifier = ImageClassifier.createFromOptions(
                context,
                optionsBuilder.build()
            )
        } catch (e: IllegalStateException) {
            classifierListener?.onError("error")
            Log.e(TAG, e.message.toString())
        }
    }

    fun classifyImage(image: ImageProxy) {
        if (imageClassifier == null) {
            setupImageClassifier()
        }

        val mpImage = BitmapImageBuilder(toBitmap(image)).build()

        val imageProcessingOptions = ImageProcessingOptions.builder()
            .setRotationDegrees(image.imageInfo.rotationDegrees)
            .build()

        val inferenceTime = SystemClock.uptimeMillis()
        imageClassifier?.classifyAsync(mpImage, imageProcessingOptions, inferenceTime)
    }

    private fun toBitmap(image: ImageProxy): Bitmap {
        val bitmapBuffer = Bitmap.createBitmap(
            image.width,
            image.height,
            Bitmap.Config.ARGB_8888
        )
        image.use { bitmapBuffer.copyPixelsFromBuffer(image.planes[0].buffer) }
        image.close()
        return bitmapBuffer
    }

    interface ClassifierListener {
        fun onError(error: String)
        fun onResults(
            results: List<Classifications>?,
            inferenceTime: Long
        )
    }

    companion object {
        private const val TAG = "ImageClassifierHelper"
    }
}