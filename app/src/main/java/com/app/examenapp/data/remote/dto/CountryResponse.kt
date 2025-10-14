package com.app.examenapp.data.remote.dto

import com.google.gson.annotations.SerializedName

data class CountryResponse (
    @SerializedName("common") val common: String,
    @SerializedName("official") val official: String,
)
