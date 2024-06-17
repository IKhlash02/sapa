package com.example.sapa.ui.screen.question

import android.util.Log
import androidx.compose.foundation.Image
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
import androidx.compose.material3.LinearProgressIndicator
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
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.example.sapa.di.Injection
import com.example.sapa.model.DictionaryData
import com.example.sapa.model.StageDetail
import com.example.sapa.ui.MainViewModel
import com.example.sapa.ui.ViewModelFactory
import com.example.sapa.ui.component.OptionButton
import com.example.sapa.ui.screen.StageViewModelFactory
import com.example.sapa.ui.screen.common.UiState
import com.example.sapa.ui.theme.PacificBlue2
import com.example.sapa.ui.theme.SAPATheme
import com.example.sapa.ui.theme.nunitoFontFamily
import kotlinx.coroutines.launch


@Composable
fun QuestionScreen(
    id: Int,
    modifier: Modifier = Modifier,
    navigateBack: () -> Unit,
    navigateFinish: () -> Unit,
    QuestionViewModel: QuestionViewModel = viewModel(
        factory = StageViewModelFactory(Injection.provideStageRepository())
    )

) {
    QuestionViewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                QuestionViewModel.getDetailStages()
            }

            is UiState.Success -> {
                Log.d("stages", "data: ${uiState.data}")
                QuestionContent(
                    id = id,
                    modifier = modifier,
                    navigateBack = navigateBack,
                    questions = uiState.data,
                    navigateFinish = navigateFinish
                )
            }

            is UiState.Error -> {}
        }

    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuestionContent(
    modifier: Modifier = Modifier,
    id: Int,
    questions : List<StageDetail>,
    navigateBack: () -> Unit,
    navigateFinish: () -> Unit
) {
    var currentQuestionIndex by remember { mutableIntStateOf(0) }

    val context = LocalContext.current
    val viewModel: MainViewModel = viewModel(
        factory = ViewModelFactory(
            Injection.provideUserRepository(context)
        )
    )
    val userData = viewModel.userData.collectAsState().value

    var progress by remember { mutableFloatStateOf(0F) }
    val currentQuestion = questions[currentQuestionIndex]
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }
    var isAnswerCorrect by remember { mutableStateOf(true) }

    Log.d("soal", "index: $currentQuestionIndex")
    Log.d("soal", "progress: $progress")

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
                text = "${userData.heart}",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontFamily = nunitoFontFamily,
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold,
                )
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        QuestionCard(modifier = Modifier.weight(1f), imageUrl = currentQuestion.Image)
        Spacer(modifier = Modifier.height(16.dp))


        OptionButton(currentQuestion.option1, onClick = {
            showBottomSheet = true
            isAnswerCorrect = currentQuestion.answer == currentQuestion.option1
        }
        )
        OptionButton(currentQuestion.option2, onClick = {
            showBottomSheet = true
            isAnswerCorrect = currentQuestion.answer == currentQuestion.option2
        })
        OptionButton(currentQuestion.option3, onClick = {
            showBottomSheet = true
            isAnswerCorrect = currentQuestion.answer == currentQuestion.option3
        })
        OptionButton(currentQuestion.option4, onClick = {
            showBottomSheet = true
            isAnswerCorrect = currentQuestion.answer == currentQuestion.option4
        })


        Spacer(modifier = Modifier.height(30.dp))

        if (showBottomSheet) {

            ModalBottomSheet(
                shape = RectangleShape,
                containerColor = if (isAnswerCorrect) Color(0xFFD7FFB8) else Color(0xFFFFDFE0),
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
                        text = if (isAnswerCorrect) "Benar" else "Salah",
                        style = TextStyle(
                            fontSize = 24.sp,
                            fontFamily = nunitoFontFamily,
                            fontWeight = FontWeight.ExtraBold,
                            color = if (isAnswerCorrect) Color(0xFF58A700) else Color(0xFFEA2B26),
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

                            if(!isAnswerCorrect){
                                viewModel.decreaseHeart()
                            }

                            if (userData.heart == 0 && !isAnswerCorrect) {
                                navigateBack()
                            } else {
                                progress += 1F/questions.size
                                if (progress >= 1F) {
                                    viewModel.increasePoint()
                                    if (userData.completed <= id) {
                                        viewModel.updateUserComplete(id + 1)
                                    }
                                    navigateFinish()
                                } else {
                                    currentQuestionIndex++
                                }
                            }
                        },
                        colorButton = if (isAnswerCorrect) Color(0xFF58CC02) else Color(0xFFFF4B4C),
                        colorText = Color(0xFFFFFFFF)
                    )

                }

            }
        }
    }
}

@Composable
fun ProgressBar(progress: Float, modifier: Modifier = Modifier) {
    LinearProgressIndicator(
        progress = { progress },
        modifier = modifier
            .height(13.dp),
        color = Color(0xFFFFB703),
        trackColor = Color.White,
        strokeCap = StrokeCap.Round,
    )
}

@Preview(showBackground = true)
@Composable
fun QuestionScreenPreview() {
    SAPATheme {
        QuestionScreen(id = 2, navigateBack = {}, navigateFinish = {})
    }
}

@Composable
fun QuestionCard(modifier: Modifier = Modifier, imageUrl: String) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ),

        ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier.padding(vertical = 20.dp),
                text = "Jawab pertanyaan berikut:",
                style = TextStyle(
                    fontSize = 24.sp,
                    fontFamily = nunitoFontFamily,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF000000),
                    textAlign = TextAlign.Center,
                ),
            )

            Image(
                modifier = Modifier
                    .fillMaxSize(),
                painter = rememberAsyncImagePainter(imageUrl),
                contentDescription = "image description",
                contentScale = ContentScale.FillBounds
            )
        }
    }
}