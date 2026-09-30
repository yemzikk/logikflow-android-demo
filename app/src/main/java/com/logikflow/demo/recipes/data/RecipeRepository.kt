package com.logikflow.demo.recipes.data

import com.logikflow.demo.recipes.data.model.Recipe

object RecipeRepository {

	private val recipes = listOf(
		Recipe(
			id = "shakshuka",
			title = "Shakshuka",
			minutes = 25,
			ingredients = listOf("4 eggs", "1 can tomatoes", "1 onion", "2 cloves garlic", "1 tsp cumin", "1 tsp paprika"),
			steps = listOf("Soften the onion and garlic.", "Add spices and tomatoes, simmer 10 minutes.", "Crack in the eggs, cover and cook until set."),
		),
		Recipe(
			id = "dal",
			title = "Tadka dal",
			minutes = 35,
			ingredients = listOf("1 cup toor dal", "1 tomato", "1 tsp mustard seeds", "1 tsp cumin seeds", "2 dried chillies", "Ghee"),
			steps = listOf("Pressure-cook the dal until soft.", "Temper mustard, cumin and chillies in ghee.", "Pour the tempering over the dal."),
		),
		Recipe(
			id = "pesto-pasta",
			title = "Pesto pasta",
			minutes = 20,
			ingredients = listOf("200 g pasta", "1 bunch basil", "30 g pine nuts", "40 g parmesan", "Olive oil"),
			steps = listOf("Blend basil, nuts, cheese and oil.", "Cook the pasta.", "Toss with pesto and a splash of pasta water."),
		),
		Recipe(
			id = "banana-bread",
			title = "Banana bread",
			minutes = 70,
			ingredients = listOf("3 ripe bananas", "200 g flour", "100 g sugar", "80 g butter", "1 egg", "1 tsp baking soda"),
			steps = listOf("Mash bananas and mix with butter, sugar and egg.", "Fold in flour and soda.", "Bake at 175 °C for 55 minutes."),
		),
	)

	fun all(): List<Recipe> = recipes

	fun byId(id: String): Recipe? = recipes.firstOrNull { it.id == id }
}
