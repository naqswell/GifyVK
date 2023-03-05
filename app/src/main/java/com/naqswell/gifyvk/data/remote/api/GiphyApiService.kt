package com.naqswell.gifyvk.data.remote.api

import com.naqswell.gifyvk.BuildConfig
import com.naqswell.gifyvk.Constants.GIPHY_BASE_URL
import com.naqswell.gifyvk.data.entities.getbyid.GetByIdResponse
import com.naqswell.gifyvk.data.entities.search.SearchResponse
import com.naqswell.gifyvk.data.remote.resultapi.ResultCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GiphyApiService {

    @GET("gifs/{id}")
    suspend fun getGifById(@Path("id") id: String): Result<GetByIdResponse>

    @GET("gifs/search")
    suspend fun getGifsBySearchQuery(
        @Query("q") q: String,
        @Query("limit") limit: Int = 25,
        @Query("offset") offset: Int = 0,
        @Query("rating") rating: String = "g",
        @Query("lang") lang: String = "en",
        @Query("random_id") randomId: String = "",
        @Query("bundle") bundle: String = "",
    ): Result<SearchResponse>

    companion object {
        private val loggingInterceptor =
            HttpLoggingInterceptor().apply { setLevel(HttpLoggingInterceptor.Level.BODY) }

        private val okHttpClient by lazy {
            OkHttpClient.Builder()
                .addInterceptor(ApiInterceptor(BuildConfig.GIPHY_API_KEY))
                .addInterceptor(loggingInterceptor)
                .build()
        }

        private val retrofit: Retrofit by lazy {
            Retrofit.Builder()
                .baseUrl(GIPHY_BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(ResultCallAdapterFactory())
                .build()
        }

        val apiService: GiphyApiService by lazy {
            retrofit.create(GiphyApiService::class.java)
        }
    }
}