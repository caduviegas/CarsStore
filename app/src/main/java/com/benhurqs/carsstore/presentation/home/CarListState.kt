package com.benhurqs.carsstore.presentation.home

import com.benhurqs.carsstore.domain.model.Car

sealed class CarListState {
    object Loading : CarListState()
    object Empty : CarListState()
    class Success(val data: List<Car>) : CarListState()
    class Error(val errorMessage: String) : CarListState()
}