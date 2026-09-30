package com.logikflow.demo.recipes.ui.favorites

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.logikflow.demo.recipes.data.FavoritesRepository
import com.logikflow.demo.recipes.data.SharedPreferencesFavoritesStore
import com.logikflow.demo.recipes.data.model.Recipe
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class FavoritesViewModel(application: Application) : AndroidViewModel(application) {

	private val repository = FavoritesRepository(SharedPreferencesFavoritesStore(application))

	val favorites: StateFlow<List<Recipe>> = repository.recipes
		.map { repository.newestFirst() }
		.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), repository.newestFirst())

	fun isFavorite(recipeId: String) = repository.isFavorite(recipeId)

	fun toggle(recipeId: String) = repository.toggle(recipeId)
}
