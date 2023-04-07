package com.colornative.seattleplacesearch.network

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.colornative.seattleplacesearch.model.Place
import kotlinx.coroutines.launch

class SearchViewModel(private val repository: PlaceRepository) : ViewModel() {

    constructor() : this(PlaceRepository()) {
        // Optional initialization code
    }

    private val _venues = MutableLiveData<List<Place>>()
    val venues: LiveData<List<Place>>
        get() = _venues

    private val _error = MutableLiveData<String>()
    val error: LiveData<String>
        get() = _error

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean>
        get() = _loading

    fun searchVenues(query: String,ll: String) {
        viewModelScope.launch {
            _loading.value = true
            val result = repository.searchPlaces(query,ll)
            _loading.value = false
            if (result.isSuccess) {
                _venues.value = result.getOrNull()
            } else {
                _error.value = result.exceptionOrNull()?.message
            }
        }
    }
}