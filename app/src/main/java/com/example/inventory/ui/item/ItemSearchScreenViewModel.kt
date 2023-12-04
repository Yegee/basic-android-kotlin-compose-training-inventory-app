package com.example.inventory.ui.item

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ItemSearchScreenViewModel : ViewModel() {

    var itemUiState by mutableStateOf(ItemUiState())
        private set

    // MutableLiveData for holding the search query
    private val _searchQuery = MutableLiveData<String>()
    val searchQuery: LiveData<String>
        get() = _searchQuery

    // Function to set the search query
    fun setSearchQuery(query: String) {
        _searchQuery.value = query
    }

    data class ItemSearchUiState(
        val itemSearchDetails: ItemSearchDetails = ItemSearchDetails(),
        val isEntryValid: Boolean = false
    )

    data class ItemSearchDetails(
        val id: Int = 0,
        val name: String = "",
        val price: String = "",
        val quantity: String = "",
    )
}