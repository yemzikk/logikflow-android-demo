package com.logikflow.demo.recipes

import com.logikflow.demo.recipes.data.RecipeRepository
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class RecipeRepositoryTest {

	@Test
	fun findsRecipesById() {
		assertEquals("Shakshuka", RecipeRepository.byId("shakshuka")?.title)
		assertNull(RecipeRepository.byId("missing"))
	}
}
