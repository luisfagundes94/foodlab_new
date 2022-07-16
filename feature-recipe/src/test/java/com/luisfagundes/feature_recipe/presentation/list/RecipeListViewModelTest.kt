package com.luisfagundes.feature_recipe.presentation.list

import androidx.paging.PagingData
import androidx.paging.map
import com.luisfagundes.commons_testing.MainCoroutineRule
import com.luisfagundes.commons_testing.model.RecipeFactory
import com.luisfagundes.usecases.GetRecipes
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(JUnit4::class)
class RecipeListViewModelTest {

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private val recipeFactory = RecipeFactory()
    private val introRecipePagingData = PagingData.from(
        listOf(
            recipeFactory.createIntro(),
            recipeFactory.createIntro()
        )
    )

    private val useCase: GetRecipes = mockk()
    private lateinit var viewModel: RecipeListViewModel

    @Before
    fun setUp() {
        viewModel = RecipeListViewModel(useCase)
    }

    @Test
    fun `SHOULD validate paging data WHEN calling recipeListPagingData`() =
        runTest {
            // Arrange
            coEvery { useCase.invoke(any()) } returns flowOf(introRecipePagingData)

            val expectedPagingData = introRecipePagingData

            // Act
            val result = viewModel.getRecipesPagingData().first()

            // Assert
            result.map { introRecipeResult ->
                expectedPagingData.map {
                    assertEquals(it.title, introRecipeResult.title)
                }
            }
        }

    @Test(expected = RuntimeException::class)
    fun `SHOULD throw an exception when calling the useCase returns an exception`() =
        runTest {
            // Arrange
            coEvery { useCase.invoke(any()) }.throws(RuntimeException())

            // Act
            viewModel.getRecipesPagingData()
        }

}