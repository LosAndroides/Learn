package com.losandroides.learn.domain

import com.losandroides.learn.framework.coVerifyOnce
import com.losandroides.learn.framework.relaxedMockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName

@ExperimentalCoroutinesApi
@DisplayName("ItemsUseCase test")
class ItemsUseCaseTest {

    private val testDispatcher = StandardTestDispatcher()
    private val itemRepository: ItemRepository = relaxedMockk()

    private lateinit var useCase: ItemsUseCase

    @BeforeEach
    fun setUp() {
        useCase = ItemsUseCase(
            itemRepository
        )
    }

    @Test
    @DisplayName("""
        WHEN useCase is invoked
        THEN call repository
    """)
    fun test() {
        runTest(testDispatcher) {
            useCase()
        }

        coVerifyOnce { itemRepository.getItems() }
    }
}
