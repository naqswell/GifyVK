package com.naqswell.gifyvk.data.entities.common.image

import com.google.gson.annotations.SerializedName

data class ImageFixedWidthResponse(

    @SerializedName("height") var height: String?,
    @SerializedName("width") var width: String?,
    @SerializedName("size") var size: String?,
    @SerializedName("url") var url: String?,
    @SerializedName("mp4_size") var mp4Size: String?,
    @SerializedName("mp4") var mp4: String?,
    @SerializedName("webp_size") var webpSize: String?,
    @SerializedName("webp") var webp: String?

)