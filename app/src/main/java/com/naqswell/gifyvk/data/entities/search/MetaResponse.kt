package com.naqswell.gifyvk.data.entities.search

import com.google.gson.annotations.SerializedName

data class MetaResponse(
    @SerializedName("status") var status: Int?,
    @SerializedName("msg") var msg: String?,
    @SerializedName("response_id") var responseId: String?,
)
