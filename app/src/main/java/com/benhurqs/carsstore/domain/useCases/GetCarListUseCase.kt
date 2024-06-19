package com.benhurqs.carsstore.domain.useCases

import android.util.Log
import com.benhurqs.carsstore.domain.model.Car
import com.benhurqs.carsstore.domain.repository.CarRepository
import com.benhurqs.carsstore.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetCarListUseCase (
    private val repository: CarRepository
) {
    operator fun invoke(): Flow<Resource<List<Car>>> = flow {
        try {
            emit(Resource.Loading)

            val carList = repository.getCarList()
            emit(Resource.Success(carList))

        } catch (e: Exception) {

            Log.e("GetCarUseCase", e.message ?: "Ocorreu um Error")
            emit(Resource.Error("Ocorreu um Error"))

        }
    }
}