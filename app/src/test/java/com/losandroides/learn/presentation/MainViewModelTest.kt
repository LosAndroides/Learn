package com.losandroides.learn.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.losandroides.learn.domain.ItemsUseCase
import com.losandroides.learn.domain.model.Item
import com.losandroides.learn.framework.CoroutinesTestRule
import com.losandroides.learn.framework.coVerifyOnce
import com.losandroides.learn.framework.relaxedMockk
import io.mockk.coEvery
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest
import org.amshove.kluent.shouldBeEqualTo
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class MainViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    private val testCoroutineScope = TestScope(coroutinesTestRule.testDispatcher)

    private val itemsUseCase: ItemsUseCase = relaxedMockk()

    @Test
    fun `GIVEN some items WHEN MainViewModel is initialized THEN ItemsUseCase is called`() {
        val items = buildItems()
        coEvery { itemsUseCase() } returns items

        testCoroutineScope.runTest {
            val viewModel = buildMainViewModel()

            coVerifyOnce { itemsUseCase() }

            viewModel.viewState.value shouldBeEqualTo MainViewModel.ViewState.Content(items)
        }
    }

    private fun buildMainViewModel() = MainViewModel(
        itemsUseCase,
        testCoroutineScope
    )

    private fun buildItems() = listOf(
        Item("Item 1"),
        Item("Item 2"),
        Item("Item 3"),
    )
}
