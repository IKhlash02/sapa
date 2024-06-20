package com.example.sapa.ui.component.buttons

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
fun ButtonComponent2(
    modifier: Modifier = Modifier,
    enable: Boolean = true,
    optionText1: String,
    optionText2: String,
    onClick: () -> Unit,
    colorButton: Color = Color.White,
    colorText: Color = Color.Black,
    @DrawableRes image1: Int,
    @DrawableRes image2: Int,
) {
    ElevatedButton(
        enabled = enable,
        colors = ButtonDefaults.buttonColors(
            colorButton
        ),
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 13.dp),
        shape = RoundedCornerShape(10.dp)
    ) {
        Row(
            Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {

            Image(
                painter = painterResource(id = image1),
                contentDescription = "image description",
                contentScale = ContentScale.None
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = optionText1,
                style = TextStyle(
                    color = colorText,
                    fontFamily = nunitoFontFamily,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 18.sp,
                ),
                modifier = Modifier.padding(vertical = 8.dp)
            )
            Spacer(modifier = Modifier.weight(1F))
            Text(
                text = optionText2,
                style = TextStyle(
                    color = colorText,
                    fontFamily = nunitoFontFamily,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 18.sp,
                ),
                modifier = Modifier.padding(vertical = 8.dp)
            )

            Spacer(modifier = Modifier.width(10.dp))
            Image(
                painter = painterResource(id = image2),
                contentDescription = "image description",
                contentScale = ContentScale.None
            )


        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ButtonComponent2Preview() {
    SAPATheme {
        ButtonComponent2(
            optionText1 = "ss",
            onClick = { /*TODO*/ },
            image1 = R.drawable.google,
            optionText2 = "ss",
            image2 = R.drawable.google
        )
    }
}