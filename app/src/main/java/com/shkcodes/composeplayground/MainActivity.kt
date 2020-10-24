package com.shkcodes.composeplayground

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.Text
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.drawLayer
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.SpanStyleRange
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

class MainActivity : AppCompatActivity() {

    private val rotationModifier = Modifier.drawLayer(rotationZ = -90F)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PlaygroundTheme {
                Row {
                    sidebar()
                    mainContent()
                }
            }
        }
    }

    @Composable
    private fun mainContent() {
        Column {
            location()
        }
    }

    @Composable
    private fun location() {
        Row(modifier = Modifier.padding(top = 24.dp, start = 8.dp, end = 8.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.weight(1F)
            ) {
                Image(asset = vectorResource(id = R.drawable.ic_location))
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = AnnotatedString(
                        "Hisar, Haryana", spanStyles = listOf(
                            SpanStyleRange(
                                SpanStyle(
                                    color = Color.White
                                ), 0, 6
                            ),
                            SpanStyleRange(
                                SpanStyle(
                                    color = Color.White,
                                    fontWeight = FontWeight.Bold
                                ), 6, 14
                            )
                        )
                    )
                )
            }
            Image(asset = vectorResource(id = R.drawable.ic_menu))
        }
    }

    @Composable
    private fun sidebar() {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.width(64.dp)
                .background(color = Colors.sidebarColor)
                .padding(vertical = 8.dp)
        ) {
            Image(
                imageResource(id = R.drawable.profile),
                contentScale = ContentScale.Crop,
                modifier = Modifier.preferredSize(42.dp).clip(CircleShape)
            )
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.weight(1F).padding(top = 64.dp)
            ) {
                Text(
                    text = "First Food",
                    style = TextStyles.sidebar,
                    modifier = rotationModifier.size(100.dp)
                )
                Text(
                    text = "Salad",
                    style = TextStyles.sidebar,
                    modifier = rotationModifier.size(100.dp)
                )
                Text(
                    text = "Sea Food",
                    style = TextStyles.sidebar,
                    modifier = rotationModifier.size(100.dp)
                )
            }
        }
    }
}