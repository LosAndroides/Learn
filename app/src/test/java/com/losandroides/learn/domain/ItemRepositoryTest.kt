package com.losandroides.learn.domain

import arrow.core.right
import com.losandroides.learn.data.local.datasource.LocalItemDatasource
import com.losandroides.learn.data.network.item.datasource.RemoteItemDatasource
import com.losandroides.learn.data.network.item.model.ItemDTO
import com.losandroides.learn.data.network.item.model.ItemsDTO
import com.losandroides.learn.framework.coVerifyNever
import com.losandroides.learn.framework.coVerifyOnce
import com.losandroides.learn.framework.relaxedMockk
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@ExperimentalCoroutinesApi
@DisplayName("ItemRepository Test")
class ItemRepositoryTest {

    private val testDispatcher = StandardTestDispatcher()
    private val localItemDatasource: LocalItemDatasource = relaxedMockk()
    private val remoteItemDatasource: RemoteItemDatasource = mockk()

    private lateinit var subject: ItemRepository

    @BeforeEach
    fun setUp() {
        subject = ItemRepository(
            localItemDatasource,
            remoteItemDatasource
        )
    }

    @Test
    @DisplayName("""
        GIVEN empty database
        WHEN getItems
        THEN get items from remote
        AND save them locally
        AND return them
    """)
    fun test() {
        coEvery { localItemDatasource.isEmpty() } returns true
        coEvery { remoteItemDatasource.getItemsDTO() } returns buildItemsDTO().right()

        runTest(testDispatcher) {
            subject.getItems()

            coVerifyOnce {
                remoteItemDatasource.getItemsDTO()
                localItemDatasource.saveItems(any())
                localItemDatasource.getAllItems()
            }
        }
    }

    @Test
    @DisplayName("""
        GIVEN filled database
        WHEN getItems
        THEN return the local ones directly
    """)
    fun test2() {
        coEvery { localItemDatasource.isEmpty() } returns false

        runTest(testDispatcher) {
            subject.getItems()

            coVerifyNever {
                remoteItemDatasource.getItemsDTO()
                localItemDatasource.saveItems(any())
            }

            coVerifyOnce {
                localItemDatasource.getAllItems()
            }
        }
    }

    private fun buildItemsDTO(): ItemsDTO {
        return ItemsDTO(
            listOf(
                ItemDTO("title 1"),
                ItemDTO("title 2"),
                ItemDTO("title 3")
            )
        )
    }
}
