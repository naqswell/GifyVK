package com.naqswell.gifyvk.screens.main

import androidx.lifecycle.*
import androidx.paging.*
import com.naqswell.gifyvk.data.entities.search.GifResponse
import com.naqswell.gifyvk.data.repository.GiphyRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MainViewModel constructor(private val giphyRepository: GiphyRepository) :
    ViewModel() {

    private var lastSearchQuery: String = "meme"
    private val _gifsList = MutableLiveData<PagingData<GifResponse>>()
    suspend fun getGifsList(searchQuery: String = lastSearchQuery): LiveData<PagingData<GifResponse>> {
        val response = withContext(Dispatchers.IO) {
            giphyRepository.getGifsList(searchQuery).cachedIn(viewModelScope)
        }
        _gifsList.value = response.value
        lastSearchQuery = searchQuery
        return response
    }

    companion object {
        fun provideFactory(giphyRepository: GiphyRepository): ViewModelProvider.Factory =
            object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return MainViewModel(giphyRepository) as T
                }
            }
    }
}

