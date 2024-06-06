package com.example.sapa.ui.screen.exam

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sapa.R
import com.example.sapa.ui.screen.question.ProgressBar
import com.example.sapa.ui.theme.PacificBlue2
import com.example.sapa.ui.theme.SAPATheme


@Composable
fun ExamScreen(
    modifier: Modifier = Modifier,
    navigateBack : () -> Unit
) {
    Column(
        modifier = modifier
            .background(color = PacificBlue2)
            .padding(16.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = navigateBack,
                content = {
                    Icon(
                        modifier = Modifier.padding(0.dp),
                        imageVector = Icons.Filled.Close,
                        contentDescription = null,
                        tint = Color.Gray
                    )
                },
            )
            ProgressBar(progress = 1f, modifier.weight(1F))
            Spacer(modifier = Modifier.width(10.dp))
            Icon(imageVector = Icons.Filled.Favorite, contentDescription = null, tint = Color.Red)
            Spacer(modifier = Modifier.width(5.dp))
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
        Spacer(modifier = Modifier.height(10.dp))
        QuestionCard(
            modifier = Modifier.height(600.dp)
        )
    }

}


@Preview(showBackground = true)
@Composable
fun ExamScreenPreview(){
    SAPATheme {

        ExamScreen(navigateBack = {})
    }
}

@Composable
fun QuestionCard(modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ),

        ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier.padding(vertical = 20.dp),
                text = "Jawab pertanyaan berikut:",
                style = TextStyle(
                    fontSize = 24.sp,
//                    fontFamily = FontFamily(Font(R.font.nunito)),
                    fontWeight = FontWeight(700),
                    color = Color(0xFF000000),
                    textAlign = TextAlign.Center,
                ),
            )

            Image(
                modifier = Modifier
                    .fillMaxWidth(),
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = "image description",
                contentScale = ContentScale.FillBounds
            )
        }
    }
}