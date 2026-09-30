package com.logikflow.demo.recipes.ui.favorites

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.logikflow.demo.recipes.databinding.ActivityFavoritesBinding
import com.logikflow.demo.recipes.ui.detail.RecipeDetailActivity
import com.logikflow.demo.recipes.ui.list.RecipeAdapter
import kotlinx.coroutines.launch

class FavoritesActivity : AppCompatActivity() {

	private lateinit var binding: ActivityFavoritesBinding
	private val viewModel: FavoritesViewModel by viewModels()

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = ActivityFavoritesBinding.inflate(layoutInflater)
		setContentView(binding.root)
		setSupportActionBar(binding.toolbar)
		supportActionBar?.setDisplayHomeAsUpEnabled(true)

		val adapter = RecipeAdapter { recipe -> startActivity(RecipeDetailActivity.intent(this, recipe.id)) }
		binding.favorites.layoutManager = LinearLayoutManager(this)
		binding.favorites.adapter = adapter

		lifecycleScope.launch {
			repeatOnLifecycle(Lifecycle.State.STARTED) {
				viewModel.favorites.collect { recipes ->
					adapter.submit(recipes)
					binding.empty.isVisible = recipes.isEmpty()
				}
			}
		}
	}

	override fun onSupportNavigateUp(): Boolean {
		finish()
		return true
	}
}
