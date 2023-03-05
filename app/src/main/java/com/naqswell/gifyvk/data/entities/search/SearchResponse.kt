package com.naqswell.gifyvk.data.entities.search

import com.google.gson.annotations.SerializedName

data class SearchResponse(

    @SerializedName("data") var data: List<GifResponse>,
    @SerializedName("pagination") var pagination: PaginationResponse,
    @SerializedName("meta") var meta: MetaResponse,

    )