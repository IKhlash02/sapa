package com.example.sapa.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sapa.ui.theme.nunitoFontFamily

@Composable
fun OptionButton(
    optionText: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    colorButton: Color = Color.White,
    colorText: Color = Color.Black
) {
    ElevatedButton(
        colors = ButtonDefaults.buttonColors(
            colorButton
        ),
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 13.dp),
        shape = RoundedCornerShape(10.dp)
    ) {
        Text(
            style = TextStyle(
                color = colorText,
                fontFamily = nunitoFontFamily,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 18.sp,
            ),
            text = optionText,

            modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
        )
    }
}
