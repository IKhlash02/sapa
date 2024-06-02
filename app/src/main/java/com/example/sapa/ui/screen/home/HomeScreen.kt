package com.example.sapa.ui.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sapa.model.UnitData
import com.example.sapa.ui.component.StageItem
import com.example.sapa.ui.component.UnitItem
import com.example.sapa.ui.theme.PacificBlue
import com.example.sapa.ui.theme.SAPATheme

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = { topBar() }
    ) { innerPadding ->
        LazyColumn(
            modifier.padding(innerPadding)
        ) {
            items(UnitData.units) { item ->
                UnitItem(
                    noUnit = item.unitNo,
                    namaTopik = item.namaTopik
                )
                var stageOffsetWeight = 1f
                item.stages.forEach { stage ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = (stageOffsetWeight * 45).dp)
                    ) {
                        StageItem(stage = stage.noStage, modifier = Modifier.padding(top = 20.dp))
                    }
                    stageOffsetWeight += 1f
                }
                Spacer(modifier = modifier.height(20.dp))
            }
        }
    }
}

@Composable
private fun topBar(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(60.dp)

            .background(color = PacificBlue)
            .padding(horizontal = 17.dp),

        verticalAlignment = Alignment.CenterVertically


    ) {
        Icon(imageVector = Icons.Filled.Star, contentDescription = null, tint = Color(0xFFFFC800))
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            text = "5",
            style = TextStyle(
                fontSize = 20.sp,
//                fontFamily = FontFamily(Font(R.font.poppins)),
                color = Color.White,
                fontWeight = FontWeight(600),
            )
        )
        Text(
            modifier = Modifier.weight(1F),
            text = "Level Dasar",
            style = TextStyle(
                fontSize = 24.sp,
//                fontFamily = FontFamily(Font(R.font.nunito)),
                color = Color.White,
                fontWeight = FontWeight(800),
                textAlign = TextAlign.Center,
            )
        )
        Icon(imageVector = Icons.Filled.Favorite, contentDescription = null, tint = Color.Red)
        Spacer(modifier = Modifier.width(10.dp))
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
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    SAPATheme {
        HomeScreen()
    }
}