package com.example.angatkinmirea

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.angatkinmirea.ui.theme.AngatkinMIREATheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AngatkinMIREATheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Hello(name = "Александр", modifier = Modifier.padding(innerPadding))
                    // StyledButton()
                    // GrayContainer()
                    // CircleTopRight()
                    // CircleCenter()
                    // FirstColumnText()
                    // SecondColumnText()
                    // RowText()
                    WaterTracker()
                }
            }
        }
    }
}

@Composable
fun Hello(name: String?, modifier: Modifier = Modifier) {
    Text(
        // text = if (name != null) "Здравствуйте, $name!" else "Имя не задано",
        text = "",
        modifier = modifier
    )
}

@Preview(
    name = "portrait", showBackground = true, showSystemUi = true,
    widthDp = 400, heightDp = 800
)
@Composable
fun HelloPreviewPortrait() {
    AngatkinMIREATheme {
        Hello("Александр")
    }
}

@Preview(
    name = "landscape", showBackground = true, showSystemUi = true,
    widthDp = 800, heightDp = 400
)
@Composable
fun HelloPreviewLandscape() {
    AngatkinMIREATheme {
        Hello("Александр")
    }
}

@Preview(
    name = "round", showBackground = true, showSystemUi = true,
    widthDp = 200, heightDp = 200, backgroundColor = 0xFFFFFF00
)
@Composable
fun HelloPreviewRound() {
    AngatkinMIREATheme {
        Box(
            modifier = Modifier.size(200.dp).clip(CircleShape).background(Color.Yellow),
            contentAlignment = Alignment.Center
        ) {
            Hello("Александр")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ComposeTextPreview() {
    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = stringResource(R.string.compose_description),
            color = Color.Green,
            fontSize = 16.sp,
            fontStyle = FontStyle.Italic,
            textAlign = TextAlign.Center,
            modifier = Modifier
        )

        Text(
            text = stringResource(R.string.compose_description),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
        )

        Text(
            text = stringResource(R.string.compose_description),
            modifier = Modifier
                .background(Color.Green)
                .padding(top = 48.dp),
            color = Color.Black,
            fontSize = 24.sp,
            textDecoration = TextDecoration.Underline
        )
    }
}

@Composable
fun StyledButton() {
    Button(
        onClick = {},
        modifier = Modifier.fillMaxWidth().padding(16.dp),
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(2.dp, Color.Gray),
        elevation = ButtonDefaults.buttonElevation(4.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.LightGray,
            contentColor = Color.Black
        )
    ) {
        Text("Нажми на меня")
    }
}

@Composable
fun GrayContainer() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .background(Color.Gray),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .padding(vertical = 16.dp)
                .aspectRatio(1f)
                .clip(CircleShape)
                .background(Color.Red),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "АА",
                fontSize = 14.sp
            )
        }
    }
}

@Composable
fun CircleTopRight() {
    Box(
        modifier = Modifier
            .size(width = 240.dp, height = 120.dp)
            .background(Color.Black)
    ) {
        Image(
            painter = painterResource(id = R.drawable.circle),
            contentDescription = "red circle",
            modifier = Modifier.align(Alignment.TopEnd)
        )
    }
}

@Composable
fun CircleCenter() {
    Box(
        modifier = Modifier
            .size(width = 240.dp, height = 120.dp)
            .background(Color.Blue)
    ) {
        Image(
            painter = painterResource(id = R.drawable.circle),
            contentDescription = "purple circle",
            modifier = Modifier
                .fillMaxSize()
                .scale(scaleX = 1f, scaleY = 0.8f)
                .align(Alignment.Center),
            colorFilter = ColorFilter.tint(Color(0xFF9C27B0)),
            contentScale = ContentScale.FillBounds
        )
    }
}

@Composable
fun FirstColumnText() {
    Column {
        Text(text = "Имя: Евгений", fontSize = 14.sp)
        Text(text = "Отчество: Андреевич", fontSize = 14.sp)
        Text(text = "Фамилия: Лукашин", fontSize = 14.sp)
        Text(text = "Мобильный телефон: +7 495 495 95 95", fontSize = 14.sp)
        Text(text = "Адрес: г. Москва, 3-я улица Строителей, д. 25, кв. 12", fontSize = 14.sp)
    }
}

@Composable
fun SecondColumnText() {
    Column {
        Box(
            modifier = Modifier
                .padding(vertical = 10.dp, horizontal = 10.dp)
                .fillMaxWidth()
                .background(Color.LightGray),
            contentAlignment = Alignment.Center
        ) {
            Column (
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Имя: Евгений", fontSize = 14.sp)
                Text(text = "Отчество: Андреевич", fontSize = 14.sp)
                Text(text = "Фамилия: Лукашин", fontSize = 14.sp)
                Text(text = "Мобильный телефон: +7 495 495 95 95", fontSize = 14.sp)
                Text(text = "Адрес: г. Москва, 3-я улица Строителей, д. 25, кв. 12", fontSize = 14.sp)
            }
        }

        Box(
            modifier = Modifier
                .padding(vertical = 10.dp, horizontal = 10.dp)
                .fillMaxWidth()
                .background(Color.LightGray),
            contentAlignment = Alignment.Center
        ) {
            Column (
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Имя: Василий", fontSize = 14.sp)
                Text(text = "Отчество: Егорович", fontSize = 14.sp)
                Text(text = "Фамилия: Кузякин", fontSize = 14.sp)
                Text(text = "Мобильный телефон: —", fontSize = 14.sp)
                Text(text = "Адрес: Ивановская область, дер. Крутово, д. 4", fontSize = 14.sp)
            }
        }

        Box(
            modifier = Modifier
                .padding(vertical = 10.dp, horizontal = 10.dp)
                .fillMaxWidth()
                .background(Color.LightGray),
            contentAlignment = Alignment.Center
        ) {
            Column (
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Имя: Людмила", fontSize = 14.sp)
                Text(text = "Отчество: Прокофьевна", fontSize = 14.sp)
                Text(text = "Фамилия: Калугина", fontSize = 14.sp)
                Text(text = "Мобильный телефон: +7 495 788 78 78", fontSize = 14.sp)
                Text(text = "Адрес: Москва, Большая Никитская, д. 43, кв. 290", fontSize = 14.sp)
            }
        }
    }
}

@Composable
fun RowText() {
    Box(
        modifier = Modifier
            .padding(vertical = 10.dp, horizontal = 10.dp)
            .fillMaxWidth()
            .background(Color.LightGray),
    ) {
        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Имя: Евгений\nОтчество: Андреевич\nФамилия: Лукашин\n" +
                    "Мобильный телефон: +7 495 495 95 95\n" +
                    "Адрес: г. Москва, 3-я улица Строителей, д. 25, кв. 12",
                fontSize = 14.sp,
                textAlign = TextAlign.Center
            )
            Image(
                painter = painterResource(id = R.drawable.circle),
                contentDescription = "red circle"
            )
        }
    }
}

@Composable
fun WaterTracker() {
    var waterCount by remember { mutableStateOf(100) }
    var dayCount by remember { mutableStateOf(0) }
    val primaryColor = Color(0xFF2196F3)
    val secondaryColor = Color(0xFF03A9F4)
    val buttonTextColor = Color(0xFFFFFFFF)
    Box (modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Трекер воды", fontSize = 32.sp,
                fontWeight = FontWeight.Bold, color = primaryColor)
            Spacer(modifier = Modifier.height(48.dp))
            Text(text = "$waterCount мл", fontSize = 48.sp,
                fontWeight = FontWeight.Bold, color = secondaryColor)
            Spacer(modifier = Modifier.height(48.dp))
            Button(onClick = { waterCount += 250 }, shape = RectangleShape) {
                Text(text = "+250 мл", fontSize = 18.sp,
                    fontWeight = FontWeight.Bold, color = buttonTextColor)
            }
            Spacer(modifier = Modifier.height(48.dp))
            Button(onClick = {
                if (waterCount >= 1500) dayCount++ else dayCount = 0
                waterCount = 0
            }, shape = RectangleShape) {
                Text(text = "Завершить день", fontSize = 18.sp,
                    fontWeight = FontWeight.Bold, color = buttonTextColor)
            }
            Spacer(modifier = Modifier.height(48.dp))
            Text(text = "Дней подряд: $dayCount", fontSize = 18.sp,
                fontWeight = FontWeight.Bold, color = secondaryColor
            )
        }
    }
}