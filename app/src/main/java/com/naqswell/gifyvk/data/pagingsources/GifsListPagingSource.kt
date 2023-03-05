package com.naqswell.gifyvk.data.pagingsources

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.naqswell.gifyvk.Constants.SEARCH_GIF_INITIAL_SATATE
import com.naqswell.gifyvk.Constants.SEARCH_GIF_PAGE_OFFSET
import com.naqswell.gifyvk.data.entities.search.GifResponse
import com.naqswell.gifyvk.data.remote.api.GiphyApiService

class GifsListPagingSource(
    private val apiService: GiphyApiService, private val searchQuery: String
) : PagingSource<Int, GifResponse>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GifResponse> {
        return try {
            val currentLoadingPageKey = params.key ?: SEARCH_GIF_INITIAL_SATATE

            var gifResponseList = emptyList<GifResponse>()
            apiService.getGifsBySearchQuery(q = searchQuery, offset = currentLoadingPageKey)
                .onSuccess {
                    gifResponseList = it.data
                }
                .onFailure {}

            val prevKey =
                if (currentLoadingPageKey == SEARCH_GIF_INITIAL_SATATE)
                    null
                else currentLoadingPageKey - SEARCH_GIF_PAGE_OFFSET

            return LoadResult.Page(
                data = gifResponseList,
                prevKey = prevKey,
                nextKey = currentLoadingPageKey.plus(SEARCH_GIF_PAGE_OFFSET)
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, GifResponse>): Int? {
        return null
    }

}