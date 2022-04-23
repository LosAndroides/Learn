package com.losandroides.learn.domain

import com.losandroides.learn.data.network.item.model.ItemDTO
import com.losandroides.learn.data.network.item.model.ItemsDTO
import com.losandroides.learn.data.toDomain
import com.losandroides.learn.domain.model.Item
import org.amshove.kluent.shouldBeEqualTo
import org.junit.Test

class ItemMapperKtTest {

    @Test
    fun `GIVEN empty DTO list WHEN ItemsDTO toDomain THEN return the expected domain list`() {
        val itemsDTO = ItemsDTO(emptyList())

        val result = itemsDTO.toDomain()

        result shouldBeEqualTo emptyList()
    }

    @Test
    fun `GIVEN normal DTO list WHEN ItemsDTO toDomain THEN return the expected domain list`() {
        val titles = listOf(
            "1",
            "2"
        )
        val itemsDTO = ItemsDTO(
            titles.map { title -> ItemDTO(title) }
        )

        val result = itemsDTO.toDomain()

        val items = titles.map { title -> Item(title) }
        result shouldBeEqualTo items
    }
}
