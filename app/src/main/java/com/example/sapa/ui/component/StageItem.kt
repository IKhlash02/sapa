package com.example.sapa.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sapa.R
import com.example.sapa.ui.theme.SAPATheme

@Composable
fun StageItem(
    modifier: Modifier = Modifier,
    stage: Int,
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Image(
            modifier = Modifier
            .width(82.dp)
            .height(68.dp),
            painter = painterResource(id = R.drawable.stage_item), contentDescription = "stageItem"
        )
        Text(
            text = "$stage" ,
            style = TextStyle(
                fontSize = 40.sp,
//                fontFamily = FontFamily(Font(R.font.amaranth)),
                fontWeight = FontWeight(700),
                color = Color(0xFFFFFFFF),

                )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun StageItemPreview() {
    SAPATheme {
        StageItem(stage = 1)
    }
}