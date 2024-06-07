package com.example.sapa.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sapa.R
import com.example.sapa.ui.theme.PacificBlue2
import com.example.sapa.ui.theme.SAPATheme

@Composable
fun StageItem(
    modifier: Modifier = Modifier,
    stage: Int? = null,

) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {

        Image(
            modifier = Modifier
                .width(82.dp)
                .height(68.dp),
            painter = painterResource(id = R.drawable.stage_item), contentDescription = "stageItem",
        )
        if(stage != null){
            Text(
                text = "$stage" ,
                style = TextStyle(
                    fontSize = 40.sp,
//                fontFamily = FontFamily(Font(R.font.amaranth)),
                    fontWeight = FontWeight(700),
                    color = Color(0xFFFFFFFF),

                    )
            )
        } else{
            Image(
                modifier = Modifier.size(60.dp),
                painter = painterResource(id = R.drawable.peti), contentDescription = "stageItem",
            )
        }
    }
}


@Composable
fun StageItem1(
    modifier: Modifier = Modifier,
    stage: Int? = null,

    ) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {

        ElevatedButton(
            modifier = Modifier
                .width(68.dp)
                .height(68.dp)
                .clip(CircleShape)
                .shadow(
                    elevation = 1000.dp,
                    shape = CircleShape,
                    ambientColor = Color.Gray,
                    spotColor = Color.Black
                ),
            onClick = {},
            colors = ButtonDefaults.buttonColors(
                PacificBlue2
            ),
            elevation = ButtonDefaults.elevatedButtonElevation(
                defaultElevation = 10.dp

            )
        ){
            if(stage != null){
                Text(
                    text = "$stage" ,
                    style = TextStyle(
                        fontSize = 40.sp,
//                fontFamily = FontFamily(Font(R.font.amaranth)),
                        fontWeight = FontWeight(700),
                        color = Color(0xFFFFFFFF),

                        )
                )
            } else{
                Image(
                    modifier = Modifier.size(60.dp),
                    painter = painterResource(id = R.drawable.peti), contentDescription = "stageItem",
                )
            }
        }

    }
}


@Preview(showBackground = true)
@Composable
fun StageItemPreview() {
    SAPATheme {
        StageItem()
//        StageItem1()
    }
}

