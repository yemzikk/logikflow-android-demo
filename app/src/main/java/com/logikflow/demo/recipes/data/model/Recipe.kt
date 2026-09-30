package com.logikflow.demo.recipes.data.model

data class Recipe(
	val id: String,
	val title: String,
	val minutes: Int,
	val ingredients: List<String>,
	val steps: List<String>,
)
