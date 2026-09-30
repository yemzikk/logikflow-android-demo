package com.logikflow.demo.recipes.ui.list

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.logikflow.demo.recipes.R
import com.logikflow.demo.recipes.config.FeatureFlags
import com.logikflow.demo.recipes.data.RecipeRepository
import com.logikflow.demo.recipes.databinding.ActivityMainBinding
import com.logikflow.demo.recipes.ui.detail.RecipeDetailActivity
import com.logikflow.demo.recipes.ui.favorites.FavoritesActivity

class MainActivity : AppCompatActivity() {

	private lateinit var binding: ActivityMainBinding

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = ActivityMainBinding.inflate(layoutInflater)
		setContentView(binding.root)
		setSupportActionBar(binding.toolbar)

		val adapter = RecipeAdapter { recipe -> startActivity(RecipeDetailActivity.intent(this, recipe.id)) }
		binding.recipes.layoutManager = LinearLayoutManager(this)
		binding.recipes.adapter = adapter
		adapter.submit(RecipeRepository.all())
	}

	override fun onCreateOptionsMenu(menu: Menu): Boolean {
		if (!FeatureFlags.FAVORITES_ENABLED) return false
		menuInflater.inflate(R.menu.menu_main, menu)
		return true
	}

	override fun onOptionsItemSelected(item: MenuItem): Boolean {
		if (item.itemId == R.id.action_favorites) {
			startActivity(Intent(this, FavoritesActivity::class.java))
			return true
		}
		return super.onOptionsItemSelected(item)
	}
}
