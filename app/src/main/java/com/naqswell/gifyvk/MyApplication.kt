package com.naqswell.gifyvk

import android.app.Application
import androidx.annotation.UiThread
import com.naqswell.gifyvk.data.remote.api.GiphyApiService
import com.naqswell.gifyvk.data.repository.GiphyRepository
import com.naqswell.gifyvk.data.repository.GiphyRepositoryImpl

@UiThread
class MyApplication : Application() {

    val giphyApiService: GiphyApiService by lazy {
        GiphyApiService.apiService
    }

    val giphyRepository: GiphyRepository by lazy {
        GiphyRepositoryImpl(giphyApiService)
    }
}