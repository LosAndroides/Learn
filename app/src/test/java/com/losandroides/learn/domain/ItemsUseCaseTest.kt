package com.losandroides.learn.domain

import com.losandroides.learn.framework.coVerifyOnce
import com.losandroides.learn.framework.relaxedMockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class ItemsUseCaseTest {

    private val testDispatcher = StandardTestDispatcher()
    private val itemRepository: ItemRepository = relaxedMockk()

    private lateinit var useCase: ItemsUseCase

    @Before
    fun setUp() {
        useCase = ItemsUseCase(
            itemRepository
        )
    }

    @Test
    fun `WHEN useCase is invoked THEN call repository`() {
        runTest(testDispatcher) {
            useCase()
        }

        coVerifyOnce { itemRepository.getItems() }
    }
}
