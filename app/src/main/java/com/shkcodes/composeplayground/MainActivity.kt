package com.shkcodes.composeplayground

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.animatedFloat
import androidx.compose.animation.core.repeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.onActive
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.drawLayer
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.shkcodes.composeplayground.FoodTypes.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PlaygroundTheme {
                val navController: NavHostController = rememberNavController()
                Row {
                    sidebar(navController)
                    NavHost(
                        navController = navController,
                        startDestination = SeaFood.name,
                    ) {
                        composable(SeaFood.name) {
                            seafood()
                        }
                        composable(Salad.name) {
                            salad()
                        }
                        composable(FirstFood.name) {
                            firstFood()
                        }
                    }
                }
            }
        }
    }

    @Composable
    private fun sidebar(navController: NavHostController) {
        val selectedTab = remember { mutableStateOf(SeaFood) }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .width(64.dp)
                .background(color = Colors.sidebar)
                .padding(vertical = 8.dp)
        ) {
            Image(
                imageResource(id = R.drawable.profile),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .preferredSize(42.dp)
                    .clip(CircleShape)
            )
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .weight(1F)
                    .padding(top = 64.dp)
            ) {
                FoodTypes.values().forEach {
                    Box(
                        modifier = Modifier
                            .size(width = 64.dp, height = 100.dp)
                            .clickable(onClick = {
                                selectedTab.value = it
                                navController.navigate(it.name)
                            })
                    ) {
                        Text(
                            text = it.name,
                            style = TextStyles.sidebar.copy(
                                color = if (selectedTab.value == it) Color.White else Colors.tabUnselected,
                            ),
                            modifier = Modifier
                                .align(Alignment.Center)
                                .drawLayer(rotationZ = -90F)
                        )
                        if (selectedTab.value == it) tabSelectionAnimation(
                            modifier = Modifier
                                .align(Alignment.CenterEnd)
                                .size(32.dp)
                        )
                    }
                }
            }
        }
    }

    @Composable
    private fun tabSelectionAnimation(modifier: Modifier = Modifier) {
        val animatedProgress = animatedFloat(0f)
        onActive {
            animatedProgress.animateTo(
                1F, anim = repeatable(
                    iterations = 1,
                    animation = tween(durationMillis = 1000),
                )
            )
        }

        val strokeWidth = 6f
        val brush = SolidColor(Colors.accent)
        Canvas(modifier = modifier, onDraw = {
            if (animatedProgress.value <= 0.5) {
                drawLine(
                    brush,
                    Offset(size.width, 0F),
                    Offset(
                        size.width * (1 - animatedProgress.value),
                        size.height * animatedProgress.value
                    ),
                    strokeWidth = strokeWidth
                )
            } else {
                drawLine(
                    brush,
                    Offset(size.width, 0F),
                    Offset(size.width / 2, size.height / 2),
                    strokeWidth = strokeWidth
                )
                drawLine(
                    brush,
                    Offset(
                        size.width / 2, size.height
                                / 2
                    ),
                    Offset(
                        size.width * animatedProgress.value,
                        size.height * animatedProgress.value
                    ),
                    strokeWidth = strokeWidth
                )
            }
            drawCircle(
                brush,
                animatedProgress.value * 10F,
                Offset(size.width * 0.8F, size.height / 2F)
            )
        })
    }
}