package com.example.sapa.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sapa.data.pref.UserModel
import com.example.sapa.ui.theme.PacificBlue
import com.example.sapa.ui.theme.nunitoFontFamily

@Composable
fun TopBar(
    userModel: UserModel,
    onClick: () -> Unit,
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
            text = "${userModel.point}",
            style = TextStyle(
                fontSize = 20.sp,
                fontFamily = nunitoFontFamily,
                color = Color.White,
                fontWeight = FontWeight.SemiBold,
            )
        )
        Text(
            modifier = Modifier.weight(1F),
            text = "Level Dasar",
            style = TextStyle(
                fontSize = 24.sp,
                fontFamily = nunitoFontFamily,
                color = Color.White,
                fontWeight = FontWeight.ExtraBold,
                textAlign = TextAlign.Center,
            )
        )
        Row(
            modifier = Modifier.clickable { onClick() }
        ) {
            Icon(
                imageVector = Icons.Filled.Favorite, contentDescription = null, tint = Color.Red
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = "${userModel.heart}",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontFamily = nunitoFontFamily,
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold,
                )
            )
        }
    }
}
