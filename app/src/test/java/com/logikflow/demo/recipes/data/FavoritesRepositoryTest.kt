package com.logikflow.demo.recipes.data

import com.logikflow.demo.recipes.data.model.Favorite
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class FavoritesRepositoryTest {

	private class MemoryStore(var saved: List<Favorite> = emptyList()) : FavoritesStore {
		override fun load() = saved
		override fun save(favorites: List<Favorite>) {
			saved = favorites
		}
	}

	private var now = 1_000L
	private fun repository(store: FavoritesStore) = FavoritesRepository(store, clock = { now++ })

	@Test
	fun toggleAddsThenRemoves() {
		val store = MemoryStore()
		val repo = repository(store)

		assertTrue(repo.toggle("dal"))
		assertTrue(repo.isFavorite("dal"))
		assertEquals(listOf("dal"), store.saved.map { it.recipeId })

		assertFalse(repo.toggle("dal"))
		assertFalse(repo.isFavorite("dal"))
		assertTrue(store.saved.isEmpty())
	}

	@Test
	fun listsNewestFirst() {
		val repo = repository(MemoryStore())
		repo.toggle("dal")
		repo.toggle("shakshuka")

		assertEquals(listOf("shakshuka", "dal"), repo.newestFirst().map { it.id })
	}

	@Test
	fun dropsFavoritesForRecipesThatNoLongerExist() {
		val repo = repository(MemoryStore(listOf(Favorite("deleted-recipe", 1), Favorite("dal", 2))))

		assertEquals(listOf("dal"), repo.recipes.value.map { it.recipeId })
	}
}
