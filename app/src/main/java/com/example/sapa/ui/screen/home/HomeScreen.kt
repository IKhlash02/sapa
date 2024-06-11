package com.example.sapa.ui.screen.home

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sapa.R
import com.example.sapa.di.Injection
import com.example.sapa.model.UnitData
import com.example.sapa.ui.MainViewModel
import com.example.sapa.ui.ViewModelFactory
import com.example.sapa.ui.component.ButtonComponent2
import com.example.sapa.ui.component.StageItem
import com.example.sapa.ui.component.TopBar
import com.example.sapa.ui.component.UnitItem
import com.example.sapa.ui.theme.PacificBlue
import com.example.sapa.ui.theme.SAPATheme
import com.example.sapa.ui.theme.nunitoFontFamily
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navigateToQuestion: (Int) -> Unit,
    viewModel: MainViewModel =  viewModel(
        factory = ViewModelFactory(
            Injection.provideUserRepository(LocalContext.current)
        )
    )
    ) {
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }

    val userData = viewModel.userData.collectAsState().value
    Log.d("HomeScreen", "data: ${viewModel.userData.collectAsState().value}")


    Scaffold(
        topBar = {
            TopBar(
                userModel = userData,
                onClick = {
                    showBottomSheet = true
                }
            )
        }
    ) { innerPadding ->
        var stageOffsetWeight = 1f
        var boolean = true
        LazyColumn(
            modifier.padding(innerPadding)
        ) {
            items(UnitData.units) { item ->
                UnitItem(
                    noUnit = item.unitNo,
                    namaTopik = item.namaTopik,
                    enabled = item.stages[0].id <= userData.completed
                )
                item.stages.forEach { stage ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = (stageOffsetWeight * 47).dp)
                    ) {
                        StageItem(
                            unitId = item.id,
                            enabled = stage.id <= userData.completed,
                            stage = stage.noStage,
                            modifier = Modifier
                                .padding(top = 20.dp)
                                .clickable(enabled = stage.id <= userData.completed) {
                                    navigateToQuestion(stage.id)
                                }
                        )

                    }
                    if (boolean) {
                        stageOffsetWeight += 1f
                    } else {
                        stageOffsetWeight -= 1f
                    }

                }
                boolean = !boolean
                Spacer(modifier = modifier.height(20.dp))
            }
        }
    }

    if (showBottomSheet) {
        ModalBottomSheet(
            shape = RectangleShape,
            containerColor = Color.White,
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
                    text = if (userData.heart == 0) "Hatimu masih Penuh" else "Isi Ulang Hati",
                    style = TextStyle(
                        fontSize = 24.sp,
                        fontFamily = nunitoFontFamily,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color.Black,
                        textAlign = TextAlign.Center,
                    )
                )

                Spacer(Modifier.height(10.dp))
                ButtonComponent2(
                    enable = userData.heart != 5,
                    image1 = R.drawable.heart,
                    image2 = R.drawable.xp,
                    modifier = Modifier.padding(bottom = 30.dp),
                    optionText1 = "Isi Hati",
                    optionText2 = "${userData.point}",
                    onClick = {
                        scope.launch { sheetState.hide() }.invokeOnCompletion {
                            if (!sheetState.isVisible) {
                                showBottomSheet = false

                                viewModel.increaseHeart()
                                viewModel.decreasePoint()
                            }
                        }

                    },
                    colorButton = PacificBlue,
                    colorText = Color(0xFFFFFFFF)
                )

            }

        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    SAPATheme {
        HomeScreen(navigateToQuestion = {})
    }
}