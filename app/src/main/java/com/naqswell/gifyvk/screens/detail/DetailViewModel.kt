package com.naqswell.gifyvk.screens.detail

import androidx.lifecycle.*
import com.naqswell.gifyvk.data.entities.getbyid.GetByIdResponse
import com.naqswell.gifyvk.data.repository.GiphyRepository
import kotlinx.coroutines.launch

class DetailViewModel constructor(private val giphyRepository: GiphyRepository) :
    ViewModel() {

    private val _isErrorOccurred = MutableLiveData(false)
    val isErrorOccurred = _isErrorOccurred

    private val _gif = MutableLiveData<GetByIdResponse>()
    val gif = _gif

    fun fetchGifData(id: String) {
        viewModelScope.launch {
            giphyRepository.getGifById(id)
                .onSuccess { response ->
                    _gif.value = response
                    _isErrorOccurred.value = true
                }
                .onFailure {
                    _isErrorOccurred.value = true
                }
        }
    }

    companion object {
        fun provideFactory(giphyRepository: GiphyRepository): ViewModelProvider.Factory =
            object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return DetailViewModel(giphyRepository) as T
                }
            }
    }
}