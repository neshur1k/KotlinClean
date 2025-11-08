package com.example.angatkinmirea

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.wear.tooling.preview.devices.WearDevices
import com.example.angatkinmirea.ui.theme.AngatkinMIREATheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AngatkinMIREATheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Hello(
                        name = "Александр",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Hello(name: String?, modifier: Modifier = Modifier) {
    Text(
        text = if (name != null) "Здравствуйте, $name!" else "Имя не задано",
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
