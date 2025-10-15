package com.app.examenapp.data.remote.dto

import com.google.gson.annotations.SerializedName

data class CountryListDto(
    @SerializedName("name") val name: NameDto,
    @SerializedName("capital") val capital: List<String>?,
    @SerializedName("region") val region: String?,
    @SerializedName("flags") val flags: FlagDto?
) {
    data class NameDto(
        @SerializedName("common") val common: String,
        @SerializedName("official") val official: String
    )

    data class FlagDto(
        @SerializedName("png") val png: String?
    )
}