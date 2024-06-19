package com.benhurqs.carsstore.presentation.lead

sealed class LeadState {

    object Initial : LeadState()
    class Success(val message: String? = null) : LeadState()
    class Error(val errorMessage: String) : LeadState()

}
