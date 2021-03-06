package com.shkcodes.composeplayground

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonConstants
import androidx.compose.material.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.SolidColor
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


@Composable
fun seafood() {
    ScrollableColumn(modifier = Modifier.padding(horizontal = 16.dp)) {
        location()
        greeting()
        searchBox()
        filters()
        carousel()
        recommendations()
    }
}

@Composable
private fun recommendations() {
    Column(modifier = Modifier.padding(vertical = 16.dp)) {
        Row {
            Text(text = "Recommended Places", style = TextStyles.sidebar.copy(fontSize = 16.sp))
            Spacer(Modifier.weight(1F))
            Text(
                text = "More",
                style = TextStyles.sidebar.copy(fontSize = 16.sp, color = Colors.accent)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))

        recommendedDishes.forEach { dish ->
            ConstraintLayout(
                modifier = Modifier.fillMaxWidth().height(84.dp).padding(vertical = 16.dp),
            ) {
                val (image, card, title, subtitle, price, rating) = createRefs()
                val guideline = createGuidelineFromStart(0.15F)
                Box(
                    modifier = Modifier.padding(vertical = 16.dp).height(70.dp)
                        .constrainAs(card) {
                            end.linkTo(parent.end)
                            start.linkTo(guideline)
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom)
                            width = Dimension.fillToConstraints
                        }.background(Color.White, RoundedCornerShape(12.dp))
                ) {

                }
                Text(
                    text = dish.name,
                    style = TextStyle(
                        fontFamily = fontFamily(font(R.font.champagne_bold)),
                        fontSize = 16.sp,
                        color = Colors.dark
                    ),
                    modifier = Modifier.constrainAs(title) {
                        top.linkTo(card.top)
                        start.linkTo(image.end)
                    })
                Text(
                    text = dish.price,
                    style = TextStyle(
                        fontFamily = fontFamily(font(R.font.champagne_bold)),
                        fontSize = 14.sp,
                        color = Colors.dark
                    ),
                    modifier = Modifier.constrainAs(price) {
                        top.linkTo(card.top)
                        end.linkTo(card.end)
                    }.padding(end = 8.dp, start = 4.dp)
                )
                Text(
                    text = dish.description,
                    style = TextStyle(
                        fontFamily = fontFamily(font(R.font.champagne)),
                        fontSize = 12.sp,
                        color = Colors.dark.copy(alpha = 0.5F),
                    ),
                    modifier = Modifier.constrainAs(subtitle) {
                        top.linkTo(title.bottom)
                        linkTo(image.end, rating.start, bias = 0F)
                        width = Dimension.percent(0.4F)
                    }.padding(top = 8.dp, end = 4.dp, start = 4.dp)
                )
                Row(
                    Modifier.constrainAs(rating) {
                        end.linkTo(card.end)
                        top.linkTo(subtitle.top)
                        bottom.linkTo(subtitle.bottom)
                    }.padding(end = 8.dp, top = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        asset = vectorResource(id = R.drawable.ic_star),
                        modifier = Modifier.size(8.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "5.0",
                        color = Colors.dark.copy(alpha = 0.5F),
                        fontSize = 12.sp
                    )
                }
                Image(
                    asset = imageResource(id = dish.image),
                    modifier = Modifier.constrainAs(image) {
                        start.linkTo(guideline)
                        end.linkTo(guideline)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        height = Dimension.value(84.dp)
                        width = Dimension.value(84.dp)
                    }
                )
            }
        }
    }
}

@Composable
private fun carouselBackground(modifier: Modifier, content: @Composable () -> Unit) {
    val brush = SolidColor(Color.White)
    Box(modifier = modifier) {
        Canvas(
            modifier = Modifier,
            onDraw = {
                val path = Path().apply {
                    moveTo(0F, 20.dp.toPx())
                    cubicTo(
                        0F,
                        20.dp.toPx(),
                        5.dp.toPx(),
                        5.dp.toPx(),
                        20.dp.toPx(),
                        10.dp.toPx()
                    )
                    lineTo(140.dp.toPx(), 30.dp.toPx())
                    cubicTo(
                        140.dp.toPx(),
                        30.dp.toPx(),
                        170.dp.toPx(),
                        30.dp.toPx(),
                        170.dp.toPx(),
                        50.dp.toPx()
                    )
                    lineTo(170.dp.toPx(), 230.dp.toPx())
                    cubicTo(
                        170.dp.toPx(),
                        240.dp.toPx(),
                        170.dp.toPx(),
                        260.dp.toPx(),
                        140.dp.toPx(),
                        255.dp.toPx()
                    )
                    lineTo(35F, 235.dp.toPx())
                    cubicTo(
                        35.dp.toPx(),
                        235.dp.toPx(),
                        0.dp.toPx(),
                        245.dp.toPx(),
                        0.dp.toPx(),
                        210.dp.toPx()
                    )
                }
                drawPath(path, brush)
            })

        content()
    }
}

@Composable
private fun carousel() {
    ScrollableRow(modifier = Modifier.fillMaxWidth().padding(top = 24.dp)) {
        carouselDishes.forEach { dish ->
            carouselBackground(
                modifier = Modifier.padding(horizontal = 8.dp).size(175.dp, 275.dp)
            ) {
                Column(Modifier.padding(start = 16.dp, end = 8.dp)) {
                    Image(
                        asset = imageResource(id = dish.image),
                        modifier = Modifier.size(150.dp).align(Alignment.CenterHorizontally)
                            .offset(y = (-20).dp)
                    )
                    Row(
                        modifier = Modifier.offset(y = (-20).dp),
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
                        modifier = Modifier.offset(y = (-20).dp),
                        text = dish.name,
                        style = TextStyle(
                            fontFamily = fontFamily(font(R.font.champagne_bold)),
                            fontSize = 16.sp
                        )
                    )
                    Spacer(Modifier.height(4.dp))
                    Text(
                        modifier = Modifier.offset(y = (-20).dp),
                        text = dish.description,
                        style = TextStyle(
                            fontFamily = fontFamily(font(R.font.champagne_bold)),
                            fontSize = 14.sp,
                            color = Colors.dark.copy(alpha = 0.5F)
                        )
                    )
                    Spacer(Modifier.height(8.dp))
                    Text(
                        modifier = Modifier.offset(y = (-20).dp),
                        text = dish.price,
                        style = TextStyle(
                            fontFamily = fontFamily(font(R.font.champagne_bold)),
                            fontSize = 14.sp,
                            color = Color.Black
                        )
                    )
                    Row(
                        horizontalArrangement = Arrangement.End,
                        modifier = Modifier.fillMaxWidth().offset(y = (-10).dp)
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
                    colors = ButtonConstants.defaultButtonColors(backgroundColor = if (isSelected) Colors.accent else Colors.sidebar),
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