package com.example.sapa.ui.screen.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sapa.R
import com.example.sapa.di.Injection
import com.example.sapa.factory.MainViewModel
import com.example.sapa.factory.ViewModelFactory
import com.example.sapa.ui.theme.SAPATheme
import com.example.sapa.ui.theme.nunitoFontFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier
) {

    val context = LocalContext.current

    val viewModel: MainViewModel = viewModel(
        factory = ViewModelFactory(
            Injection.provideUserRepository(context)
        )
    )
    val userData = viewModel.userData.collectAsState().value

    var showBottomSheet by remember { mutableStateOf(true) }
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    Box(
        modifier = modifier.fillMaxWidth()
    ) {

        Image(
            modifier = Modifier.fillMaxWidth(),
            painter = painterResource(id = R.drawable.rainbow),
            contentDescription = "image description",
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier.padding(top = 60.dp)
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Image(
                    modifier = Modifier
                        .border(
                            width = 2.dp,
                            color = Color(0xFFFFFFFF),
                            shape = RoundedCornerShape(size = 103.dp)
                        )
                        .width(103.dp)
                        .height(103.dp)
                        .clip(CircleShape),
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "image description",
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = userData.name,
                    style = TextStyle(
                        fontSize = 35.sp,
                        fontFamily = nunitoFontFamily,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color(0xFF000000),
                        textAlign = TextAlign.Center,
                    )
                )
                Spacer(modifier = Modifier.height(23.dp))
            }
            Column(
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {
                Text(
                    text = "Statistik",
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontFamily = nunitoFontFamily,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF000000),
                    )
                )


                Spacer(modifier = Modifier.height(14.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    CardStat(
                        title = "${userData.point}",
                        description = "XP didapat",
                        painter = painterResource(
                            id = R.drawable.xp
                        ),
                        modifier = Modifier.weight(1f)

                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    CardStat(
                        title = "${userData.heart}",
                        description = "Nyawa tersisa",
                        painter = painterResource(
                            id = R.drawable.heart
                        ),
                        modifier = Modifier.weight(1f)
                    )
                }
                Spacer(modifier = Modifier.height(14.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
//                    CardStat(
//                        title = "72 jam",
//                        description = "Waktu dihabiskan",
//                        painter = painterResource(
//                            id = R.drawable.time
//                        )
//                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    CardStat(
                        title = "2",
                        description = "Level dicapai",
                        painter = painterResource(
                            id = R.drawable.level
                        )
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
private fun ProfileScreenPreview() {
    SAPATheme {
        ProfileScreen()
    }
}

@Composable
private fun CardStat(
    title: String,
    description: String,
    painter: Painter,
    modifier: Modifier = Modifier
) {
    Card(

        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ),

        modifier = Modifier
            .width(190.dp)
            .height(72.dp)
            .border(
                width = 2.dp,
                color = Color(0xFFE5E5E5),
                shape = RoundedCornerShape(size = 15.dp)
            ),


        ) {
        Row(
            modifier = Modifier.padding(vertical = 12.dp, horizontal = 17.dp),
        ) {
            Image(
                painter = painter,
                contentDescription = "image description",
                contentScale = ContentScale.None,
                modifier = Modifier
                    .padding(top = 5.dp)
                    .height(17.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column(

            ) {

                Text(
                    text = title,
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontFamily = nunitoFontFamily,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF000000),
                    ),
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = description,
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontFamily = nunitoFontFamily,
                        fontWeight = FontWeight.Medium,
                        color = Color(0xFF000000),
                    )
                )

            }

        }
    }
}