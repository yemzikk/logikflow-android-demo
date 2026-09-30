package com.logikflow.demo.recipes.data

import android.content.Context
import com.logikflow.demo.recipes.data.model.Favorite

interface FavoritesStore {
	fun load(): List<Favorite>
	fun save(favorites: List<Favorite>)
}

class SharedPreferencesFavoritesStore(context: Context) : FavoritesStore {

	private val prefs = context.applicationContext.getSharedPreferences("favorites", Context.MODE_PRIVATE)

	override fun load(): List<Favorite> =
		prefs.getStringSet(KEY, emptySet()).orEmpty().mapNotNull { entry ->
			val (id, savedAt) = entry.split(SEPARATOR, limit = 2).takeIf { it.size == 2 } ?: return@mapNotNull null
			savedAt.toLongOrNull()?.let { Favorite(id, it) }
		}

	override fun save(favorites: List<Favorite>) {
		prefs.edit().putStringSet(KEY, favorites.map { "${it.recipeId}$SEPARATOR${it.savedAt}" }.toSet()).apply()
	}

	private companion object {
		const val KEY = "entries"
		const val SEPARATOR = "|"
	}
}
