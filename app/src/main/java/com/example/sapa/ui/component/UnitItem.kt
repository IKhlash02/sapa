package com.example.sapa.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sapa.R
import com.example.sapa.ui.theme.PacificBlue
import com.example.sapa.ui.theme.SAPATheme
import com.example.sapa.ui.theme.nunitoFontFamily

@Composable
fun UnitItem(
    modifier: Modifier = Modifier,
    noUnit: Int,
    namaTopik: String,
    enabled: Boolean = false
){
    val colorRes = when (noUnit) {
        1 -> PacificBlue
        2 -> Color(0xFFFFB703)
        3 -> Color(0xFFFB8500)
        else -> PacificBlue
    }
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .height(110.dp),
        color = if(enabled) colorRes else Color(0xFF727272)

    ) {



    Column(
        modifier  = Modifier.padding(start = 16.dp ),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Unit $noUnit",
            style = TextStyle(
                fontSize = 30.sp,
                fontFamily = nunitoFontFamily,
                fontWeight = FontWeight.ExtraBold,
                color = Color(0xFFFFFFFF),
            )
        )
        Spacer(modifier = Modifier.height(7.dp))
        Text(
            text = namaTopik,
            style = TextStyle(
                fontSize = 20.sp,
                fontFamily = nunitoFontFamily,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFFFFFFF),

                )
        )
    }

    }
}

@Preview(showBackground = true)
@Composable
private fun UnitItemPreview(){
    SAPATheme {
        UnitItem(noUnit = 1, namaTopik = "kata ganti orang", enabled = true)
    }
}