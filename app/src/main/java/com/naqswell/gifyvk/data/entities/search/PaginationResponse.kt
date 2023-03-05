package com.naqswell.gifyvk.data.entities.search

import com.google.gson.annotations.SerializedName

data class PaginationResponse(

    @SerializedName("total_count") var totalCount: Int?,
    @SerializedName("count") var count: Int?,
    @SerializedName("offset") var offset: Int?,

)