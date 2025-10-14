package com.app.examenapp.presentation.screens.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.examenapp.domain.usecase.GetCountryUseCase
import com.app.examenapp.domain.common.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountryDetailViewModel
@Inject
constructor(
    private val getCountryUseCase: GetCountryUseCase,
) : ViewModel() {
    private val _uiState = MutableStateFlow(CountryDetailUIState())
    val uiState: StateFlow<CountryDetailUIState> = _uiState.asStateFlow()

    fun getCountry(name: String) {
        viewModelScope.launch {
            getCountryUseCase(name).collect { result ->
                _uiState.update { state ->
                    when (result) {
                        is Result.Loading ->
                            state.copy(
                                isLoading = true,
                            )
                        is Result.Success ->
                            state.copy(
                                country = result.data,
                                isLoading = false,
                                error = null,
                            )
                        is Result.Error ->
                            state.copy(
                                error = result.exception.message,
                                isLoading = false,
                            )
                    }
                }
            }
        }
    }
}
