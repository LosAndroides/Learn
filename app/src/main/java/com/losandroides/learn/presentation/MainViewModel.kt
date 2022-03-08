package com.losandroides.learn.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.losandroides.learn.domain.ItemsUseCase
import com.losandroides.learn.domain.model.Item
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val itemsUseCase: ItemsUseCase
) : ViewModel() {

    init {
        getItems()
    }

    private val _viewState = MutableStateFlow<ViewState>(ViewState.Content())
    val viewState: StateFlow<ViewState> = _viewState

    private fun getItems() {
        viewModelScope.launch(Dispatchers.IO) {
            val items = itemsUseCase()
            _viewState.value = ViewState.Content(items)
        }
    }

    sealed class ViewState {
        data class Content(val items: List<Item> = emptyList()) : ViewState()
    }
}
