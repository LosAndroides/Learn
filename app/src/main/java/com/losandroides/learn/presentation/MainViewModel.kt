package com.losandroides.learn.presentation

import androidx.lifecycle.ViewModel
import com.losandroides.learn.domain.ItemsUseCase
import com.losandroides.learn.domain.model.Item
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val itemsUseCase: ItemsUseCase,
    private val scope: CoroutineScope
) : ViewModel() {

    init {
        getItems()
    }

    private val _viewState = MutableStateFlow<ViewState>(ViewState.Content())
    val viewState: StateFlow<ViewState> = _viewState

    private fun getItems() {
        scope.launch(Dispatchers.IO) {
            val items = itemsUseCase()
            _viewState.value = ViewState.Content(items)
        }
    }

    sealed class ViewState {
        data class Content(val items: List<Item> = emptyList()) : ViewState()
    }
}
