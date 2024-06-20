package com.example.sapa.ui.component.buttons

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sapa.R
import com.example.sapa.ui.theme.SAPATheme
import com.example.sapa.ui.theme.nunitoFontFamily

@Composable
fun IconButton(
    optionText: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    colorButton: Color = Color.White,
    colorText: Color = Color.Black,
    @DrawableRes image: Int,
) {
    ElevatedButton(
        colors = ButtonDefaults.buttonColors(
            colorButton
        ),
        elevation = ButtonDefaults.elevatedButtonElevation(
            defaultElevation = 5.dp
        ),
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 13.dp),
        shape = RoundedCornerShape(10.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = image),
                contentDescription = "image description",
                contentScale = ContentScale.None
            )

            Text(
                text = optionText,
                style = TextStyle(
                    color = colorText,
                    fontFamily = nunitoFontFamily,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 18.sp,
                ),
                modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun IconButtonPreview(){
    SAPATheme {
        IconButton(optionText = "ss", onClick = { /*TODO*/ }, image = R.drawable.google)
    }
}