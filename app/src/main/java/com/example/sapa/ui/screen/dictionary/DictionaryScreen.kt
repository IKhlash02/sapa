package com.example.sapa.ui.screen.dictionary

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.sapa.model.DictionaryData
import com.example.sapa.ui.theme.SAPATheme
import com.example.sapa.ui.theme.nunitoFontFamily

@Composable
fun DictionaryScreen(
    modifier: Modifier = Modifier,
    dictionaryLetters: List<String> = DictionaryData.dictionaryLetters,
    dictionaryNumbers: List<String> = DictionaryData.dictionaryNumbers
){
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp)
            .verticalScroll(rememberScrollState())
            .background(color = Color.White),
       verticalArrangement = Arrangement.Center

    ) {
        Text(
            text = "Letter Dictionary",
            style = TextStyle(
                fontSize = 30.sp,
                fontFamily = nunitoFontFamily,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF000000),
            )
        )
        Spacer(modifier = Modifier.height(15.dp))
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(15.dp)
        ) {
           items(dictionaryLetters) {letter ->
               val painter = rememberAsyncImagePainter(letter)
               ElevatedCard(
                   elevation = CardDefaults.cardElevation(
                       defaultElevation = 5.dp
                   )
               ) {
                   Image(
                       painter = painter,
                       contentDescription = null,
                       modifier = Modifier.size(400.dp),
                       contentScale = ContentScale.Crop
                   )

               }
           }
        }
        Spacer(modifier = Modifier.height(30.dp))
        Text(
            text = "Number Dictionary ",
            style = TextStyle(
                fontSize = 30.sp,
                fontFamily = nunitoFontFamily,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF000000),
            )
        )
        Spacer(modifier = Modifier.height(15.dp))
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            items(dictionaryNumbers) {number ->
                val painter = rememberAsyncImagePainter(number)
                ElevatedCard(
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 5.dp
                    )
                ) {
                    Image(
                        painter = painter,
                        contentDescription = null,
                        modifier = Modifier.size(400.dp),
                        contentScale = ContentScale.Crop
                    )

                }
            }
        }

    }
}


@Preview(showBackground = true)
@Composable
private fun DictionaryScreenPreview(){
    SAPATheme {
        DictionaryScreen()
    }

}