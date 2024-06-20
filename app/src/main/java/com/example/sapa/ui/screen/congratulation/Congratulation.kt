package com.example.sapa.ui.screen.congratulation

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sapa.R
import com.example.sapa.di.Injection
import com.example.sapa.factory.MainViewModel
import com.example.sapa.factory.ViewModelFactory
import com.example.sapa.ui.component.AnimatedPreloader
import com.example.sapa.ui.component.buttons.OptionButton
import com.example.sapa.ui.component.SoundPlayer
import com.example.sapa.ui.theme.SAPATheme
import com.example.sapa.ui.theme.nunitoFontFamily


@Composable
fun CongratulationScreen(
    modifier: Modifier = Modifier,
    navigateBack: () -> Unit,
) {

    val context = LocalContext.current
    val viewModel: MainViewModel = viewModel(
        factory = ViewModelFactory(
            Injection.provideUserRepository(context)
        )
    )
    val userData = viewModel.userData.collectAsState().value

    SoundPlayer(soundResId = R.raw.victory)
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    )
    {
        AnimatedPreloader(
            animatation = R.raw.congratulation_animation,
            modifier = Modifier
                .width(376.dp)
                .height(251.dp)
        )
        Spacer(modifier = Modifier.height(50.dp))
        Text(
            text = "Yeay, kamu berhasil!",
            style = TextStyle(
//                fontSize = 25.sp,
//                fontFamily = FontFamily(Font(R.font.nunito)),
                fontWeight = FontWeight(700),
                color = Color(0xFFFFB703),
                textAlign = TextAlign.Center,
            )
        )
        Spacer(modifier = Modifier.height(49.dp))
        Row {
            TotalComponent(
                image = R.drawable.total_xp,
                level = userData.point,
                color = Color(0xFFF2BC3B),
                type = R.drawable.xp
            )
            Spacer(modifier = Modifier.width(20.dp))
            TotalComponent(
                image = R.drawable.total_level,
                level = userData.level,
                color = Color(0xFFFB8500),
                type = R.drawable.level_2
            )
        }
        Spacer(modifier = Modifier.height(100.dp))
        OptionButton(
            modifier = Modifier.padding(16.dp),
            optionText = "KEMBALI KE HALAMAN UTAMA",
            onClick = { navigateBack() },
            colorButton = Color(0xFF219EBC),
            colorText = Color.White
        )
    }
}


@Preview(showBackground = true)
@Composable
private fun CongratulationPreview() {
    SAPATheme {
        CongratulationScreen(navigateBack = {})
    }
}


@Composable
private fun TotalComponent(
    modifier: Modifier = Modifier,
    @DrawableRes image: Int,
    level: Int,
    color: Color,
    @DrawableRes type: Int,

    ) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = image),
            contentDescription = null
        )
        Row(
            modifier = Modifier.offset(x = 0.dp, y = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = type),
                contentDescription = "image description",
                contentScale = ContentScale.None,
                modifier = Modifier
                    .padding(0.dp)
                    .height(25.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "$level",
                style = TextStyle(
                    fontSize = 18.sp,
                    fontFamily = nunitoFontFamily,
                    fontWeight = FontWeight.ExtraBold,
                    color = color,
                )
            )
        }
    }
}

