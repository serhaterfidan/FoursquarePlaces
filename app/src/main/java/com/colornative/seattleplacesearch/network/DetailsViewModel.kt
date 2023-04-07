package com.colornative.seattleplacesearch.network

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.colornative.seattleplacesearch.model.PlaceDetails
import kotlinx.coroutines.launch

class DetailsViewModel(private val repository: PlaceRepository) : ViewModel() {

    private val _place = MutableLiveData<PlaceDetails>()
    val place: LiveData<PlaceDetails>
        get() = _place

    private val _error = MutableLiveData<String>()
    val error: LiveData<String>
        get() = _error

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean>
        get() = _loading

    fun getVenueDetails(id: String) {
        viewModelScope.launch {
            _loading.postValue(true)
            try {
                val result = repository.getPlaceDetails(id)
                if (result.isSuccess) {
                    _place.postValue(result.getOrNull())
                } else {
                    _error.postValue(result.exceptionOrNull()?.message)
                }
            } catch (e: Exception) {
                _error.postValue(e.message)
            } finally {
                _loading.postValue(false)
            }
        }
    }
}