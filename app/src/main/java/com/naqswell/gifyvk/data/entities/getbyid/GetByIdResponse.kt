package com.naqswell.gifyvk.data.entities.getbyid

import com.google.gson.annotations.SerializedName
import com.naqswell.gifyvk.data.entities.search.GifResponse
import com.naqswell.gifyvk.data.entities.search.MetaResponse

data class GetByIdResponse(
    @SerializedName("data") var data: GifResponse,
    @SerializedName("meta") var meta: MetaResponse,
)
