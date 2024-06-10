package com.example.sapa.ui.screen.intro

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sapa.R
import com.example.sapa.ui.component.AnimatedPreloader
import com.example.sapa.ui.component.IconButton
import com.example.sapa.ui.component.OptionButton
import com.example.sapa.ui.theme.SAPATheme
import com.example.sapa.ui.theme.nunitoFontFamily
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IntroScreen(
    modifier: Modifier = Modifier,
    navigateHome: () -> Unit
) {


    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }

    Column(
        modifier = modifier.fillMaxSize().padding(horizontal = 27.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    )
    {

        AnimatedPreloader(
            animatation = R.raw.hand_animation,
            modifier = Modifier
                .width(376.dp)
                .height(251.dp)
        )
        Text(
            text = "SAPA",
            style = TextStyle(
                fontSize = 35.sp,
                fontFamily = nunitoFontFamily,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF219EBC),
                textAlign = TextAlign.Center,
                letterSpacing = 3.5.sp,
            )
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Aplikasi pembelajaran bahasa isyarat yang menyenangkan dan efektif!",
            style = TextStyle(
                fontSize = 20.sp,
                fontFamily = nunitoFontFamily,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF4B4B4B),
                textAlign = TextAlign.Center,
            )
        )
        Spacer(modifier = Modifier.height(200.dp))
        OptionButton(
            modifier = Modifier.padding(0.dp),
            optionText = "MARI MULAI",
            onClick =  navigateHome ,
            colorButton = Color(0xFF219EBC),
            colorText = Color.White
        )
        Spacer(modifier = Modifier.height(10.dp))
        OptionButton(
            modifier = Modifier.padding(0.dp),
            optionText = "SAYA SUDAH PUNYA AKUN",
            onClick = {
                showBottomSheet = true
            },
            colorButton = Color.White,
            colorText = Color(0xFF219EBC)
        )

        if (showBottomSheet) {
            ModalBottomSheet(
                containerColor = Color(0xFF023047),
                onDismissRequest = {
                    showBottomSheet = false
                },
                sheetState = sheetState
            ) {
                // Sheet content

                Column(
                    modifier = Modifier.padding(horizontal = 16.dp)
                ) {
                    IconButton(
                        image = R.drawable.google,
                        modifier = Modifier.padding(bottom = 30.dp),
                        optionText = "Log In with Google", onClick = {
                            scope.launch { sheetState.hide() }.invokeOnCompletion {
                                if (!sheetState.isVisible) {
                                    showBottomSheet = false
                                }
                            }

                        },
                        colorButton = Color.White,
                        colorText = Color.Black
                    )

                }

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun IntoScreenPreview() {
    SAPATheme {
        IntroScreen(navigateHome = {})
    }
}
