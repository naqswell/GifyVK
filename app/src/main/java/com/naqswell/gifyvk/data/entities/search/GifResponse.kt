package com.naqswell.gifyvk.data.entities.search

import com.google.gson.annotations.SerializedName
import com.naqswell.gifyvk.data.entities.common.image.ImagesResponse

data class GifResponse(

    @SerializedName("type") var type: String?,
    @SerializedName("id") var id: String?,
    @SerializedName("url") var url: String?,
    @SerializedName("slug") var slug: String?,
    @SerializedName("bitly_gif_url") var bitlyGifUrl: String?,
    @SerializedName("bitly_url") var bitlyUrl: String?,
    @SerializedName("embed_url") var embedUrl: String?,
    @SerializedName("username") var username: String?,
    @SerializedName("source") var source: String?,
    @SerializedName("title") var title: String?,
    @SerializedName("rating") var rating: String?,
    @SerializedName("content_url") var contentUrl: String?,
    @SerializedName("source_tld") var sourceTld: String?,
    @SerializedName("source_post_url") var sourcePostUrl: String?,
    @SerializedName("is_sticker") var isSticker: Int?,
    @SerializedName("import_datetime") var importDatetime: String?,
    @SerializedName("trending_datetime") var trendingDatetime: String?,
    @SerializedName("images") var images: ImagesResponse?,

    )