package com.naqswell.gifyvk.data.entities.common.image

import com.google.gson.annotations.SerializedName

data class ImagesResponse(
    @SerializedName("original") var original: ImageOriginalResponse?,
    @SerializedName("fixed_height") var fixedHeight: ImageFixedHeightResponse?,
    @SerializedName("fixed_width") var fixedWidth: ImageFixedWidthResponse?,
    @SerializedName("preview_gif") var previewGif: ImagesPreviewGifData?,
)
