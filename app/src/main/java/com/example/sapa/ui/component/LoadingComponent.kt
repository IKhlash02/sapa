package com.example.sapa.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.sapa.ui.theme.SAPATheme


@Composable
fun LoadingComponent(
    modifier: Modifier = Modifier
) {

  Box(modifier = modifier.fillMaxSize(),
      contentAlignment = Alignment.Center){
      CircularProgressIndicator(
          modifier = Modifier.size(64.dp),
          color = MaterialTheme.colorScheme.secondary,
          trackColor = MaterialTheme.colorScheme.surfaceVariant,
          strokeWidth = 5.dp
      )
  }
}

@Preview(showBackground = true)
@Composable
fun LoadingComponentPreview(){
    SAPATheme {
        LoadingComponent()
    }
}