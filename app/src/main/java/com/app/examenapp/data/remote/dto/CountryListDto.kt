package com.app.examenapp.data.remote.dto

import com.google.gson.annotations.SerializedName

data class CountryListDto(
    @SerializedName("name") val name: List<CountryResponse>,
    )