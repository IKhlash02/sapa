package com.example.sapa.ui.component.camerax

import android.util.Log
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.Preview
import androidx.camera.core.resolutionselector.AspectRatioStrategy
import androidx.camera.core.resolutionselector.ResolutionSelector
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.LifecycleCameraController
import androidx.camera.view.PreviewView
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import com.example.sapa.helper.ImageClassifierHelper
import org.tensorflow.lite.task.vision.classifier.Classifications
import java.util.concurrent.Executors


@Composable
fun CameraPreviewScreen(
    controller: LifecycleCameraController,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    Executors.newSingleThreadExecutor()

    val lifecycleOwner = LocalLifecycleOwner.current
    AndroidView(
        modifier = modifier,
        factory = { ctx ->
            PreviewView(ctx).apply {
                this.controller = controller
                controller.bindToLifecycle(lifecycleOwner)
            }
        },
        update = { previewView ->
            val cameraProviderFuture = ProcessCameraProvider.getInstance(context)
            cameraProviderFuture.addListener({

//                val imageClassifierHelper = ImageClassifierHelper(
//                    context = context,
//                    classifierListener = object : ImageClassifierHelper.ClassifierListener{
//                        override fun onError(error: String) {
//                            Log.d("ExamScreen", "erro: $error")
//                        }
//
//                        override fun onResults(
//                            results: List<Classifications>?,
//                            inferenceTime: Long
//                        ) {
//                            Log.d("ExamScreen", "$results")
//                        }
//
//                    }
//                )

//                val resolutionSelector = ResolutionSelector.Builder()
//                    .setAspectRatioStrategy(AspectRatioStrategy.RATIO_16_9_FALLBACK_AUTO_STRATEGY)
//                    .build()
//                val imageAnalyzer = ImageAnalysis.Builder()
//                    .setResolutionSelector(resolutionSelector)
//                    .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
//                    .setOutputImageFormat(ImageAnalysis.OUTPUT_IMAGE_FORMAT_RGBA_8888)
//                    .build()
//                imageAnalyzer.setAnalyzer(Executors.newSingleThreadExecutor()) { image ->
//                    imageClassifierHelper.classifyImage(image)
//                }

                val cameraProvider = cameraProviderFuture.get()
                val preview = Preview.Builder().build().also {
                    it.setSurfaceProvider(previewView.surfaceProvider)
                }
                val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

                try {
                    cameraProvider.unbindAll()
                    cameraProvider.bindToLifecycle(
                        context as LifecycleOwner,
                        cameraSelector,
                        preview,
//                        imageAnalyzer
                    )
                } catch (e: Exception) {
                    // Handle exceptions
                }
            }, ContextCompat.getMainExecutor(context))
        }
    )
}