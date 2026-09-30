package com.logikflow.demo.recipes.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.logikflow.demo.recipes.R
import com.logikflow.demo.recipes.data.model.Recipe
import com.logikflow.demo.recipes.databinding.ItemRecipeBinding

class RecipeAdapter(
	private val onClick: (Recipe) -> Unit,
) : RecyclerView.Adapter<RecipeAdapter.ViewHolder>() {

	private var recipes: List<Recipe> = emptyList()

	fun submit(items: List<Recipe>) {
		recipes = items
		notifyDataSetChanged()
	}

	override fun getItemCount() = recipes.size

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
		ViewHolder(ItemRecipeBinding.inflate(LayoutInflater.from(parent.context), parent, false))

	override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(recipes[position])

	inner class ViewHolder(private val binding: ItemRecipeBinding) : RecyclerView.ViewHolder(binding.root) {
		fun bind(recipe: Recipe) {
			binding.title.text = recipe.title
			binding.minutes.text = binding.root.context.getString(R.string.recipe_minutes, recipe.minutes)
			binding.root.setOnClickListener { onClick(recipe) }
		}
	}
}
