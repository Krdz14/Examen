package com.app.examenapp.presentation.screens.home

import com.app.examenapp.domain.model.Country

data class HomeUiState(
    val countryList: List<Country> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
)