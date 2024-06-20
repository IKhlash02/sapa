package com.example.sapa.ui.screen.exam

import android.util.Log
import androidx.camera.core.CameraSelector
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sapa.di.Injection
import com.example.sapa.model.Classification
import com.example.sapa.factory.MainViewModel
import com.example.sapa.factory.ViewModelFactory
import com.example.sapa.ui.component.camera.CameraPreview1
import com.example.sapa.ui.component.camera.SignImageAnalyzer
import com.example.sapa.ui.component.camera.TfLiteSignClassifier
import com.example.sapa.ui.component.buttons.OptionButton
import com.example.sapa.ui.screen.question.ProgressBar
import com.example.sapa.ui.theme.PacificBlue2
import com.example.sapa.ui.theme.SAPATheme
import com.example.sapa.ui.theme.nunitoFontFamily
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExamScreen(
    modifier: Modifier = Modifier,
    id: Int,
    name: String,
    navigateBack: () -> Unit,
    navigateFinish: () -> Unit,
    viewModel: MainViewModel = viewModel(
        factory = ViewModelFactory(
            Injection.provideUserRepository(LocalContext.current)
        )
    )
) {

    val userData = viewModel.userData.collectAsState().value

    var classification by remember {
        mutableStateOf(emptyList<Classification>())
    }

    var progress by remember {
        mutableFloatStateOf(0f)
    }

    val levelUser = remember(name) {
        when {
            '3' in name -> 3
            '2' in name -> 2
            else -> 1
        }
    }

    val letters = remember(name) {
        when {
            '3' in name -> ('1'..'9').toList()
            '2' in name -> ('O'..'Z').toList()
            else -> ('A'..'N').toList()
        }
    }
    val randomLetters = remember { letters.shuffled().take(5) }

    var currentQuestionIndex by remember { mutableIntStateOf(0) }
    val currentQuestion = randomLetters[currentQuestionIndex]
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }

    val context = LocalContext.current

    val analyzer = remember {
        SignImageAnalyzer(
            classifier = TfLiteSignClassifier(
                context = context
            ),
            onResults = {
                Log.d("main1", it.toString())
                classification = it
                if (currentQuestion.toString() in it[0].name) {
                    showBottomSheet = true
                }
            }
        )
    }
    val controller = remember {
        LifecycleCameraController(context).apply {
            cameraSelector = CameraSelector.DEFAULT_FRONT_CAMERA
            setEnabledUseCases(CameraController.IMAGE_ANALYSIS)
            setImageAnalysisAnalyzer(
                ContextCompat.getMainExecutor(context),
                analyzer,
            )
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
            ProgressBar(progress = progress, modifier.weight(1F))
            Spacer(modifier = Modifier.width(10.dp))
            Icon(imageVector = Icons.Filled.Favorite, contentDescription = null, tint = Color.Red)
            Spacer(modifier = Modifier.width(5.dp))
            Text(
                text = "5",
                style = TextStyle(
                    fontSize = 20.sp,
                    color = Color.White,
                    fontWeight = FontWeight(600),
                )
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        QuestionCard(
            modifier = Modifier.weight(1F),
            classification,
            controller,
            currentQuestion,
            showBottomSheet = { showBottomSheet = it }
        )
        Spacer(modifier = Modifier.height(70.dp))

        if (showBottomSheet) {
            ModalBottomSheet(
                shape = RectangleShape,
                containerColor = Color(0xFFD7FFB8),
                onDismissRequest = {
                    showBottomSheet = false
                },
                sheetState = sheetState
            ) {
                // Sheet content

                Column(
                    modifier = Modifier.padding(horizontal = 16.dp)
                ) {
                    Text(
                        text = "Benar",
                        style = TextStyle(
                            fontSize = 24.sp,
                            fontFamily = nunitoFontFamily,
                            fontWeight = FontWeight.ExtraBold,
                            color = Color(0xFF58A700),
                            textAlign = TextAlign.Center,
                        )
                    )

                    Spacer(Modifier.height(10.dp))
                    OptionButton(
                        modifier = Modifier.padding(bottom = 30.dp),
                        optionText = "Lanjutkan", onClick = {
                            scope.launch { sheetState.hide() }.invokeOnCompletion {
                                if (!sheetState.isVisible) {
                                    showBottomSheet = false
                                }
                            }
                            progress += 0.2F
                            if (progress >= 1F) {
                                viewModel.increasePoint(30)
                                if (userData.completed <= id) {
                                    viewModel.updateUserComplete(id + 1)
                                    viewModel.updateUserLevel(levelUser)
                                }
                                navigateFinish()
                            } else {
                                currentQuestionIndex++
                            }

                        },
                        colorButton = Color(0xFF58CC02),
                        colorText = Color(0xFFFFFFFF)
                    )

                }

            }
        }

    }


}

@Preview(showBackground = true)
@Composable
fun ExamScreenPreview() {
    SAPATheme {

        ExamScreen(navigateBack = {}, navigateFinish = {}, id = 1, name = "question 1")
    }
}

@Composable
fun QuestionCard(
    modifier: Modifier = Modifier,
    classifications: List<Classification>,
    controller: LifecycleCameraController,
    currentQuestion: Char,
    showBottomSheet: (Boolean) -> Unit

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
            Row {
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Pertanyaan",
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontFamily = nunitoFontFamily,
                            fontWeight = FontWeight.SemiBold,
                            color = Color(0xFF000000),
                            textAlign = TextAlign.Center,
                        )
                    )
                    Text(
                        text = "$currentQuestion",
                        style = TextStyle(
                            fontSize = 40.sp,
                            fontFamily = nunitoFontFamily,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF000000),
                            textAlign = TextAlign.Center,
                        )
                    )
                }
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Jawaban",
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontFamily = nunitoFontFamily,
                            fontWeight = FontWeight.SemiBold,
                            color = Color(0xFF000000),
                            textAlign = TextAlign.Center,
                        )
                    )
                    classifications.forEach {

                        Text(
                            text = it.name,
                            style = TextStyle(
                                fontSize = 40.sp,
                                fontFamily = nunitoFontFamily,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF000000),
                                textAlign = TextAlign.Center,
                            )
                        )


                    }

                }
            }
            CameraPreview1(controller = controller, modifier = Modifier.fillMaxSize())
        }
    }
}