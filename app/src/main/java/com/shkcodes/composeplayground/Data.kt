package com.shkcodes.composeplayground

import androidx.annotation.DrawableRes

enum class FoodTypes {
    SeaFood, Salad, FirstFood
}

enum class Filter {
    Foods, Wine, Mediterranean, Tomato, India, Butter
}

data class Dish(
    val name: String,
    val description: String,
    val price: String,
    @DrawableRes val image: Int
)

val carouselDishes = listOf(
    Dish("Double Patty Burger", "With Fresh Onions", "$12.00", R.drawable.burger),
    Dish("Hot Dog Sausage", "With Stale Onions", "$11.00", R.drawable.hot_dog),
    Dish("Mario\'s Special Pizza", "With No Onions", "$18.00", R.drawable.pizza)
)

val recommendedDishes = listOf(
    Dish(
        "Beef Steak",
        "Very popular with noisy tourists, it is amazing",
        "$24.00",
        R.drawable.plate_1
    ),
    Dish("Mutton Steak", "Not really recommended", "$38.00", R.drawable.plate_2),
    Dish("Vegan Stuff", "Not as popular as the above one", "$500.05", R.drawable.plate_3)
)