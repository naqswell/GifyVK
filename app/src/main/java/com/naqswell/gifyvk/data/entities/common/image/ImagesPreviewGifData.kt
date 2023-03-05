package com.naqswell.gifyvk.data.entities.common.image

import com.google.gson.annotations.SerializedName

data class ImagesPreviewGifData(

    @SerializedName("height") var height: String?,
    @SerializedName("width") var width: String?,
    @SerializedName("size") var size: String?,
    @SerializedName("url") var url: String?

)