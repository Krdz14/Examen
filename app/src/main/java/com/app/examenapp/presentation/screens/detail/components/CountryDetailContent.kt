package com.app.examenapp.presentation.screens.detail.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.app.examenapp.domain.model.Country

@Suppress("ktlint:standard:function-naming")
@Composable
fun CountryDetailContent(country: Country) {
    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        AsyncImage(
            model = country.flagUrl,
            contentDescription = country.name,
            modifier = Modifier.size(200.dp),
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = country.name,
            style = MaterialTheme.typography.headlineMedium,
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("Capital")
                Text(country.capital)
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("Region")
                Text(country.region)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}
