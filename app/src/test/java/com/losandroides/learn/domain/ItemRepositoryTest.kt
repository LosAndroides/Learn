package com.losandroides.learn.domain

import com.losandroides.learn.data.network.item.RemoteItemDatasource
import com.losandroides.learn.data.network.item.model.ItemDTO
import com.losandroides.learn.data.network.item.model.ItemsDTO
import com.losandroides.learn.domain.model.Item
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.amshove.kluent.shouldBeEqualTo
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class ItemRepositoryTest {

    private val testDispatcher = StandardTestDispatcher()
    private val remoteItemDatasource: RemoteItemDatasource = mockk()

    private lateinit var subject: ItemRepository

    @Before
    fun setUp() {
        subject = ItemRepository(
            remoteItemDatasource
        )
    }

    @Test
    fun `GIVEN some items WHEN call repository THEN get the right data`() {
        coEvery { remoteItemDatasource.getItemsDTO() } returns buildItemsDTO()

        runTest(testDispatcher) {
            val items = subject.getItems()

            items shouldBeEqualTo buildItems()
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

    private fun buildItems(): List<Item> {
        return listOf(
            Item("title 1"),
            Item("title 2"),
            Item("title 3")
        )
    }
}
