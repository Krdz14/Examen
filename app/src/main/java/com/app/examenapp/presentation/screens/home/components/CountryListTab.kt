package com.app.examenapp.presentation.screens.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.app.examenapp.domain.model.Country

@Suppress("ktlint:standard:function-naming")
@Composable
fun CountryListTab(
    countryList: List<Country>,
    onCountryClick: (String) -> Unit,
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        // Mock data para el Lab 3
        items(
            items = countryList,
            key = { it.name },
        ) { country ->
            CountryCard(
                country = country,
                onClick = { onCountryClick(country.name) },
            )
        }
    }
}
