package com.app.examenapp.presentation.screens.detail

import com.app.examenapp.domain.model.Country

data class CountryDetailUIState(
    val country: Country? = null,
    val isLoading: Boolean = false,
    val error: String? = null,
)