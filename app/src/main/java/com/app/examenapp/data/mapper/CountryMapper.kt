package com.app.examenapp.data.mapper

import com.app.examenapp.data.remote.dto.CountryDto
import com.app.examenapp.domain.model.Country

fun CountryDto.toDomain(): Country =
    Country(
        name = name.replaceFirstChar { it.uppercase() },
        capital = capital,
        region = region,
    )