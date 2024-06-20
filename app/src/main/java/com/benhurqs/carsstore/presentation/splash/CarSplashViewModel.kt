package com.benhurqs.carsstore.presentation.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.benhurqs.carsstore.domain.useCases.LeadUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CarSplashViewModel (
    private val leadUseCase: LeadUseCase
) : ViewModel() {

    init {
        sendLeadRoutine()
    }

    private fun sendLeadRoutine() {
        viewModelScope.launch(Dispatchers.IO) {
            leadUseCase.sendLeadRoutine.execute()
        }
    }
}