package com.naqswell.gifyvk.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.naqswell.gifyvk.Constants
import com.naqswell.gifyvk.data.entities.getbyid.GetByIdResponse
import com.naqswell.gifyvk.data.entities.search.GifResponse
import com.naqswell.gifyvk.data.pagingsources.GifsListPagingSource
import com.naqswell.gifyvk.data.remote.api.GiphyApiService

interface GiphyRepository {
    suspend fun getGifsList(searchQuery: String): LiveData<PagingData<GifResponse>>
    suspend fun getGifById(id: String): Result<GetByIdResponse>
}

class GiphyRepositoryImpl(
    private val apiService: GiphyApiService,
) : GiphyRepository {
    override suspend fun getGifsList(searchQuery: String): LiveData<PagingData<GifResponse>> {
        return Pager(
            config = PagingConfig(
                pageSize = Constants.SEARCH_GIF_PAGE_OFFSET,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                GifsListPagingSource(apiService, searchQuery)
            }
        ).liveData
    }

    override suspend fun getGifById(id: String): Result<GetByIdResponse> {
        return apiService.getGifById(id)
    }
}