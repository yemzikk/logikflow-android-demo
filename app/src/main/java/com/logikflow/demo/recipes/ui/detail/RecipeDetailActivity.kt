package com.logikflow.demo.recipes.ui.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.logikflow.demo.recipes.R
import com.logikflow.demo.recipes.config.FeatureFlags
import com.logikflow.demo.recipes.data.RecipeRepository
import com.logikflow.demo.recipes.databinding.ActivityRecipeDetailBinding
import com.logikflow.demo.recipes.ui.favorites.FavoritesViewModel

class RecipeDetailActivity : AppCompatActivity() {

	private lateinit var binding: ActivityRecipeDetailBinding
	private val favoritesViewModel: FavoritesViewModel by viewModels()

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = ActivityRecipeDetailBinding.inflate(layoutInflater)
		setContentView(binding.root)
		setSupportActionBar(binding.toolbar)
		supportActionBar?.setDisplayHomeAsUpEnabled(true)

		val recipe = RecipeRepository.byId(intent.getStringExtra(EXTRA_RECIPE_ID).orEmpty())
		if (recipe == null) {
			finish()
			return
		}

		title = recipe.title
		binding.minutes.text = getString(R.string.recipe_minutes, recipe.minutes)
		binding.ingredients.text = recipe.ingredients.joinToString("\n") { "• $it" }
		binding.steps.text = recipe.steps.mapIndexed { i, step -> "${i + 1}. $step" }.joinToString("\n\n")

		binding.favorite.isVisible = FeatureFlags.FAVORITES_ENABLED
		renderFavorite(favoritesViewModel.isFavorite(recipe.id))
		binding.favorite.setOnClickListener { renderFavorite(favoritesViewModel.toggle(recipe.id)) }
	}

	private fun renderFavorite(isFavorite: Boolean) {
		binding.favorite.setIconResource(if (isFavorite) R.drawable.ic_favorite else R.drawable.ic_favorite_border)
		binding.favorite.setText(if (isFavorite) R.string.favorite_remove else R.string.favorite_add)
	}

	override fun onSupportNavigateUp(): Boolean {
		finish()
		return true
	}

	companion object {
		private const val EXTRA_RECIPE_ID = "recipe_id"

		fun intent(context: Context, recipeId: String): Intent =
			Intent(context, RecipeDetailActivity::class.java).putExtra(EXTRA_RECIPE_ID, recipeId)
	}
}
