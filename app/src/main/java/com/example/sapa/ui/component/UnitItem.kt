package com.example.sapa.ui.component

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
import com.example.sapa.ui.theme.PacificBlue
import com.example.sapa.ui.theme.SAPATheme

@Composable
fun UnitItem(
    modifier: Modifier = Modifier,
    noUnit: Int,
    namaTopik: String,
){
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .height(110.dp),
        color = PacificBlue

    ) {

    Column(
        modifier  = Modifier.padding(start = 16.dp, top = 22.dp )
    ) {
        Text(
            text = "Unit $noUnit",
            style = TextStyle(
                fontSize = 30.sp,
//                fontFamily = FontFamily(Font(R.font.nunito)),
                fontWeight = FontWeight(800),
                color = Color(0xFFFFFFFF),
            )
        )
        Spacer(modifier = Modifier.height(17.dp))
        Text(
            text = namaTopik,
            style = TextStyle(
                fontSize = 20.sp,
//                fontFamily = FontFamily(Font(R.font.nunito)),
                fontWeight = FontWeight(700),
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
        UnitItem(noUnit = 1, namaTopik = "kata ganti orang")
    }
}