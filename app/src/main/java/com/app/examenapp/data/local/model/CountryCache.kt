package com.app.examenapp.data.local.model

import com.app.examenapp.domain.model.Country

data class CountryCache(
    val countryList: List<Country>,
    val lastUpdate: Long,
    val offset: Int,
    val totalCount: Int
)