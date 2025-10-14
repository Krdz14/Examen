package com.app.examenapp.data.remote.dto

import com.google.gson.annotations.SerializedName

data class CountryDto(
    @SerializedName("name") val name: String,
    @SerializedName("capital") val capital: String,
    @SerializedName("region") val region: String,
)