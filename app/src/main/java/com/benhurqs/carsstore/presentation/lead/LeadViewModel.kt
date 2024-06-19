package com.benhurqs.carsstore.presentation.lead

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.benhurqs.carsstore.domain.model.Lead
import com.benhurqs.carsstore.domain.useCases.LeadUseCase
import com.benhurqs.carsstore.domain.useCases.util.ValidationResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LeadViewModel(
    private val leadUseCase: LeadUseCase
) : ViewModel() {

    private val _emailState = MutableStateFlow<LeadState>(LeadState.Initial)
    val emailState: StateFlow<LeadState> get() = _emailState

    private val _nameState = MutableStateFlow<LeadState>(LeadState.Initial)
    val nameState: StateFlow<LeadState> get() = _nameState

    private val _leadState = MutableStateFlow<LeadState>(LeadState.Initial)
    val leadState: StateFlow<LeadState> get() = _leadState

    fun saveLead(carId: Long, nomeLead: String, emailLead: String) {
        viewModelScope.launch(Dispatchers.IO) {
            leadUseCase.leadValidation(email = emailLead, name = nomeLead).collect { result ->
                when (result) {

                    is ValidationResult.EmailError -> {
                        _emailState.value =
                            LeadState.Error(errorMessage = result.errorMessage)
                    }

                    ValidationResult.EmailSuccess -> {
                        _emailState.value =
                            LeadState.Success()
                    }

                    is ValidationResult.NameError -> {
                        _nameState.value =
                            LeadState.Error(errorMessage = result.errorMessage)
                    }

                    ValidationResult.NameSuccess -> {
                        _nameState.value =
                            LeadState.Success()
                    }

                    is ValidationResult.Success -> {

                        _emailState.value =
                            LeadState.Success()

                        _nameState.value =
                            LeadState.Success()

                        _leadState.value =
                            LeadState.Success(message = result.message)

                        leadUseCase.saveLead(
                            Lead(
                                carId = carId,
                                nomeLead = nomeLead,
                                emailLead = emailLead
                            )
                        )
                    }
                }
            }
        }
    }
}