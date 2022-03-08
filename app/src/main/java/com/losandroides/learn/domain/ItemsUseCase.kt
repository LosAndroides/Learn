package com.losandroides.learn.domain

import com.losandroides.learn.domain.model.Item
import javax.inject.Inject

class ItemsUseCase @Inject constructor(
    private val itemRepository: ItemRepository
) {
    suspend operator fun invoke(): List<Item> = itemRepository.getItems()
}
