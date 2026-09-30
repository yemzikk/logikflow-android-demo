package com.logikflow.demo.recipes.data

import com.logikflow.demo.recipes.data.model.Favorite
import com.logikflow.demo.recipes.data.model.Recipe
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class FavoritesRepository(
	private val store: FavoritesStore,
	private val recipeLookup: (String) -> Recipe? = RecipeRepository::byId,
	private val clock: () -> Long = System::currentTimeMillis,
) {

	private val favorites = MutableStateFlow(store.load().filter { recipeLookup(it.recipeId) != null })

	val recipes: StateFlow<List<Favorite>> = favorites.asStateFlow()

	fun isFavorite(recipeId: String): Boolean = favorites.value.any { it.recipeId == recipeId }

	fun toggle(recipeId: String): Boolean {
		val nowFavorite = !isFavorite(recipeId)
		val next = if (nowFavorite) {
			favorites.value + Favorite(recipeId, clock())
		} else {
			favorites.value.filterNot { it.recipeId == recipeId }
		}
		favorites.value = next
		store.save(next)
		return nowFavorite
	}

	fun newestFirst(): List<Recipe> =
		favorites.value.sortedByDescending { it.savedAt }.mapNotNull { recipeLookup(it.recipeId) }
}
