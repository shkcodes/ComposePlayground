package com.shkcodes.composeplayground

import android.os.Bundle
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.font
import androidx.compose.ui.text.font.fontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

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
        Column(modifier = Modifier.padding(horizontal = 16.dp)) {
            location()
            greeting()
            searchBox()
            filters()
            carousel()
        }
    }

    @Composable
    private fun carousel() {
        ScrollableRow(modifier = Modifier.fillMaxWidth().padding(top = 24.dp)) {
            for (item in CarouselItems.values()) {
                Card(
                    modifier = Modifier.padding(horizontal = 8.dp).size(175.dp, 275.dp),
                    backgroundColor = Color.White,
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Column(Modifier.padding(start = 16.dp, end = 8.dp)) {
                        Image(
                            asset = imageResource(id = item.image),
                            modifier = Modifier.size(150.dp).align(Alignment.CenterHorizontally)
                        )
                        Row(
                            Modifier.padding(top = 8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(
                                asset = vectorResource(id = R.drawable.ic_star),
                                modifier = Modifier.size(8.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "5.0",
                                color = Colors.dark.copy(alpha = 0.5F),
                                fontSize = 12.sp
                            )
                        }
                        Spacer(Modifier.height(4.dp))
                        Text(
                            text = item.title,
                            style = TextStyle(
                                fontFamily = fontFamily(font(R.font.champagne_bold)),
                                fontSize = 16.sp
                            )
                        )
                        Spacer(Modifier.height(4.dp))
                        Text(
                            text = item.subtitle,
                            style = TextStyle(
                                fontFamily = fontFamily(font(R.font.champagne_bold)),
                                fontSize = 14.sp,
                                color = Colors.dark.copy(alpha = 0.5F)
                            )
                        )
                        Spacer(Modifier.height(8.dp))
                        Text(
                            text = item.price,
                            style = TextStyle(
                                fontFamily = fontFamily(font(R.font.champagne_bold)),
                                fontSize = 14.sp,
                                color = Color.Black
                            )
                        )
                        Row(
                            horizontalArrangement = Arrangement.End,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Box(
                                modifier = Modifier.background(Colors.favoriteButton, CircleShape)
                                    .padding(4.dp)
                            ) {
                                Image(asset = vectorResource(id = R.drawable.ic_favorite))
                            }
                        }
                    }
                }
            }
        }
    }

    @OptIn(ExperimentalLayout::class)
    @Composable
    private fun filters() {
        val selectedIndices = mutableStateListOf<Filter>()
        Box(modifier = Modifier.padding(top = 24.dp)) {
            FlowRow(crossAxisSpacing = 16.dp, mainAxisSpacing = 8.dp) {
                Filter.values().forEach {
                    val isSelected = selectedIndices.contains(it)
                    Button(
                        onClick = {
                            if (isSelected) selectedIndices.remove(it)
                            else selectedIndices.add(it)
                        },
                        backgroundColor = if (isSelected) Colors.accent else Colors.sidebar,
                        shape = CircleShape
                    ) {
                        Text(
                            text = "$it",
                            style = TextStyles.default.copy(
                                fontFamily = fontFamily(font(R.font.champagne_bold)),
                                color = if (isSelected) Colors.dark else Color.White.copy(alpha = 0.7F)
                            ),
                        )
                    }
                }
            }
        }
    }

    @Composable
    private fun searchBox() {
        OutlinedButton(
            modifier = Modifier.padding(top = 24.dp),
            onClick = { },
            border = BorderStroke(1.dp, Color.White.copy(alpha = 0.2F)),
            backgroundColor = Colors.dark,
            shape = CircleShape
        ) {
            Text(
                text = "Search Place",
                style = TextStyles.default.copy(color = Color.White.copy(alpha = 0.7F))
            )
            Spacer(modifier = Modifier.weight(1F))
            Image(asset = vectorResource(id = R.drawable.ic_search))
        }
    }

    @Composable
    private fun greeting() {
        Column(
            modifier = Modifier.padding(top = 36.dp),
        ) {
            Text(
                text = "Hi Shashank,", style = TextStyles.default.copy(
                    fontSize = 24.sp, fontFamily = fontFamily(
                        font(R.font.champagne_bold)
                    ),
                    fontWeight = FontWeight.Bold
                )
            )
            Text(
                text = "Let's start your journey of food and eating",
                style = TextStyles.default.copy(
                    fontSize = 20.sp,
                    color = Color.White.copy(alpha = 0.7F)
                )
            )
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
                                    color = Color.White,
                                    fontFamily = fontFamily(font(R.font.champagne))
                                ), 0, 6
                            ),
                            SpanStyleRange(
                                SpanStyle(
                                    color = Color.White,
                                    fontWeight = FontWeight.Bold,
                                    fontFamily = fontFamily(font(R.font.champagne))
                                ), 6, 14
                            )
                        )
                    )
                )
            }
            Image(
                asset = imageResource(id = R.drawable.ic_menu),
                modifier = Modifier.size(28.dp)
            )
        }
    }

    @Composable
    private fun sidebar() {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.width(64.dp)
                .background(color = Colors.sidebar)
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

enum class Filter {
    Foods, Wine, Mediterranean, Tomato, India, Butter
}

enum class CarouselItems(
    val title: String,
    val subtitle: String,
    val price: String,
    @DrawableRes val image: Int
) {
    Burger("Double Patty Burger", "With Fresh Onions", "$12.00", R.drawable.burger),
    HotDog("Hot Dog Sausage", "With Stale Onions", "$11.00", R.drawable.hot_dog),
    Pizza("Mario\'s Special Pizza", "With No Onions", "$18.00", R.drawable.pizza)
}