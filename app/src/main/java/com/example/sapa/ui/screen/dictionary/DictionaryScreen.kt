package com.example.sapa.ui.screen.dictionary

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ElevatedCard
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.sapa.model.DictionaryData
import com.example.sapa.ui.theme.SAPATheme

@Composable
fun DictionaryScreen(
    modifier: Modifier = Modifier,
    dictionary: List<String> = DictionaryData.dictionary
){
    var currentIndex by remember { mutableIntStateOf(0) }
    Box(

        modifier = modifier
            .fillMaxSize()
            .background(color = Color.White)
            .clickable {
                currentIndex = (currentIndex + 1) % dictionary.size
            },
        contentAlignment = Alignment.Center

    ) {

        val painter = rememberAsyncImagePainter(dictionary[currentIndex])
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


@Preview(showBackground = true)
@Composable
private fun DictionaryScreenPreview(){
    SAPATheme {
        DictionaryScreen()
    }

}