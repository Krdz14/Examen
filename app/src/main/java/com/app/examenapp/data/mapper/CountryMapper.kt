package com.app.examenapp.data.mapper

import com.app.examenapp.data.remote.dto.CountryDto
import com.app.examenapp.data.remote.dto.CountryListDto
import com.app.examenapp.domain.model.Country

fun CountryDto.toDomain(): Country =
    Country(
        name = name.common.replaceFirstChar { it.uppercase() },
        capital = capital?.firstOrNull() ?: "Unknown",
        region = region ?: "Unkwon",
        flagUrl = flags?.png ?:"No flag"
    )
fun CountryListDto.toDomain(): Country =
    Country(
        name = name.common.replaceFirstChar { it.uppercase() },
        capital = capital?.firstOrNull() ?: "Unknown",
        region = region ?: "Unknown",
        flagUrl = flags?.png ?: "No flag"
    )