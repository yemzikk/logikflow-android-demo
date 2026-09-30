package com.logikflow.demo.recipes.ui.list

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.logikflow.demo.recipes.data.RecipeRepository
import com.logikflow.demo.recipes.databinding.ActivityMainBinding
import com.logikflow.demo.recipes.ui.detail.RecipeDetailActivity

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
}
