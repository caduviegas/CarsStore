package com.benhurqs.carsstore.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.benhurqs.carsstore.domain.useCases.GetCarListUseCase
import com.benhurqs.carsstore.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CarHomeViewModel (
    private val carList: GetCarListUseCase
) : ViewModel() {

    private val _carListState = MutableStateFlow<CarListState>(CarListState.Empty)
    val carListState: StateFlow<CarListState> get() = _carListState

    init {
        getCarList()
    }

    private fun getCarList() {
        viewModelScope.launch(Dispatchers.IO) {
            carList().collect { result ->
                when (result) {

                    is Resource.Error -> {
                        _carListState.value =
                            CarListState.Error(result.messager)
                    }

                    is Resource.Success -> {
                        _carListState.value =
                            CarListState.Success(result.data)
                    }

                    Resource.Loading -> {
                        _carListState.value =
                            CarListState.Loading
                    }
                }
            }
        }
    }
}
