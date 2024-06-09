package com.example.sapa.ui.screen.exam

import android.util.Log
import androidx.camera.view.CameraController
import androidx.camera.view.LifecycleCameraController
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import com.example.sapa.ui.component.camerax.CameraPreviewScreen
import com.example.sapa.ui.component.camerax.Classification
import com.example.sapa.ui.component.camerax.SignClassifierAnalyzer
import com.example.sapa.ui.component.camerax.TfLiteSignClassifier
import com.example.sapa.ui.screen.question.ProgressBar
import com.example.sapa.ui.theme.PacificBlue2
import com.example.sapa.ui.theme.SAPATheme


@Composable
fun ExamScreen(
    modifier: Modifier = Modifier,
    navigateBack: () -> Unit
) {

    val progress by remember {
        mutableIntStateOf(0)
    }
    val context = LocalContext.current

    var classification by remember {
        Log.d("ExamScreen", "start")
        mutableStateOf(emptyList<Classification>())
    }

    val analyzer = remember {
        SignClassifierAnalyzer(
            classifier = TfLiteSignClassifier(
                context = context
            ),
            onResults = {
                Log.d("ExamScreen", "$it")
                classification = it
            }
        )
    }


    val controller = remember {

        LifecycleCameraController(context).apply {
            try {
                setEnabledUseCases(CameraController.IMAGE_ANALYSIS)
                setImageAnalysisAnalyzer(
                    ContextCompat.getMainExecutor(context),
                    analyzer
                )
                Log.d("ExamScreen", "CameraController setup completed")
            } catch (e: Exception) {
                Log.e("ExamScreen", "Error setting up CameraController", e)
            }
        }
    }

    Column(
        modifier = modifier
            .background(color = PacificBlue2)
            .padding(16.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = navigateBack,
                content = {
                    Icon(
                        modifier = Modifier
                            .padding(0.dp)
                            .size(30.dp),
                        imageVector = Icons.Filled.Close,
                        contentDescription = null,
                        tint = Color.White
                    )
                },
            )
            ProgressBar(progress = progress.toFloat(), modifier.weight(1F))
            Spacer(modifier = Modifier.width(10.dp))
            Icon(imageVector = Icons.Filled.Favorite, contentDescription = null, tint = Color.Red)
            Spacer(modifier = Modifier.width(5.dp))
            Text(
                text = "5",
                style = TextStyle(
                    fontSize = 20.sp,
//                fontFamily = FontFamily(Font(R.font.poppins)),
                    color = Color.White,
                    fontWeight = FontWeight(600),
                )
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        QuestionCard(
            modifier = Modifier.weight(1F),
            controller
        )
        Spacer(modifier = Modifier.height(70.dp))
        classification.forEach {
            Log.d("ExamScreen", "Detect: ${it.nama}")
        }

    }


}


@Preview(showBackground = true)
@Composable
fun ExamScreenPreview() {
    SAPATheme {

        ExamScreen(navigateBack = {})
    }
}

@Composable
fun QuestionCard(
    modifier: Modifier = Modifier,
    controller: LifecycleCameraController

) {
    Card(
        modifier = modifier.fillMaxSize(),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ),

        ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier.padding(vertical = 20.dp),
                text = "Jawab pertanyaan berikut:",
                style = TextStyle(
                    fontSize = 24.sp,
//                    fontFamily = FontFamily(Font(R.font.nunito)),
                    fontWeight = FontWeight(700),
                    color = Color(0xFF000000),
                    textAlign = TextAlign.Center,
                ),
            )


            CameraPreviewScreen(
                controller = controller,
                modifier = Modifier.fillMaxSize()

            )
        }
    }
}