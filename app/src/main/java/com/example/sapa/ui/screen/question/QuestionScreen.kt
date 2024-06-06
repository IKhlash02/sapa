package com.example.sapa.ui.screen.question

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sapa.R
import com.example.sapa.ui.component.OptionButton
import com.example.sapa.ui.theme.PacificBlue2
import com.example.sapa.ui.theme.SAPATheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuestionScreen(
    modifier: Modifier = Modifier,
    id: Int,
    navigateBack: () -> Unit,
    navigateFinish: () -> Unit
) {

    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }
    var isAnswerCorrect by remember { mutableStateOf(true) }


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
                        modifier = Modifier.padding(0.dp),
                        imageVector = Icons.Filled.Close,
                        contentDescription = null,
                        tint = Color.Gray
                    )
                },
            )
            ProgressBar(progress = 1f, modifier.weight(1F))
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
        QuestionCard()
        Spacer(modifier = Modifier.height(16.dp))


        OptionButton("Option 1", onClick = {
            showBottomSheet = true
            isAnswerCorrect = true
        }
        )
        OptionButton("Option 2", onClick = {
            showBottomSheet = true
            isAnswerCorrect = false
        })
        OptionButton("Option 3", onClick = {
            showBottomSheet = true
            isAnswerCorrect = false
        })
        OptionButton("Option 4", onClick = {
            showBottomSheet = true
            isAnswerCorrect = false
        })
        Spacer(modifier = Modifier.height(10.dp))

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
//                               fontFamily = FontFamily(Font(R.font.nunito)),
                            fontWeight = FontWeight(800),
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

                            navigateFinish()
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
fun getSheetColor(isCorrect: Boolean): Color {
    return if (isCorrect) Color(0xFFD7FFB8) else Color.Red
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReusableModalBottomSheet(
    sheetState: SheetState,
    scope: CoroutineScope,
    showBottomSheet: Boolean,
    onDismiss: () -> Unit,
    onContinueClicked: () -> Unit,
    buttonText: String,
    buttonColor: Color,
    textColor: Color,
    titleText: String,
    titleColor: Color
) {
    if (showBottomSheet) {
        ModalBottomSheet(
            sheetState = sheetState,
            shape = RectangleShape,
            containerColor = Color(0xFFD7FFB8),
            onDismissRequest = onDismiss
        ) {
            Surface(color = Color(0xFFD7FFB8)) {
                Column(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = titleText,
                        style = TextStyle(
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = titleColor,
                            textAlign = TextAlign.Center
                        ),
                        modifier = Modifier.padding(top = 20.dp, bottom = 20.dp)
                    )

                    Spacer(Modifier.height(10.dp))

                    Button(
                        onClick = {
                            scope.launch {
                                sheetState.hide()
                            }.invokeOnCompletion {
                                if (!sheetState.isVisible) {
                                    onContinueClicked()
                                }
                            }
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = buttonColor),
                        modifier = Modifier
                            .padding(bottom = 30.dp)
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = buttonText,
                            color = textColor,
                            fontSize = 18.sp,
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ProgressBar(progress: Float, modifier: Modifier = Modifier) {
    LinearProgressIndicator(
        progress = progress,
        modifier = modifier
            .height(13.dp),
        color = Color(0xFFFFB703),
        trackColor = Color.White,
        strokeCap = StrokeCap.Round


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
fun QuestionCard(modifier: Modifier = Modifier) {
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
//                    fontFamily = FontFamily(Font(R.font.nunito)),
                    fontWeight = FontWeight(700),
                    color = Color(0xFF000000),
                    textAlign = TextAlign.Center,
                ),
            )

            Image(
                modifier = Modifier
                    .fillMaxWidth(),
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = "image description",
                contentScale = ContentScale.FillBounds
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewQuestionCard() {
    QuestionCard()
}