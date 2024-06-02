package com.example.sapa.ui.screen.dictionary

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.sapa.R
import com.example.sapa.ui.theme.SAPATheme

@Composable
fun DictionaryScreen(
    modifier: Modifier = Modifier
){
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center

    ) {
        Image(
            modifier = Modifier
                .width(273.dp)
                .height(473.dp),
            painter = painterResource(id = R.drawable.dic), contentDescription = null
        )
    }
}


@Preview(showBackground = true)
@Composable
private fun DictionaryScreenPreview(){
    SAPATheme {
        DictionaryScreen()
    }

}