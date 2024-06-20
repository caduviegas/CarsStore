package com.benhurqs.carsstore.data.repository

import com.benhurqs.carsstore.data.remote.CarService
import com.benhurqs.carsstore.domain.model.Car
import com.benhurqs.carsstore.domain.repository.CarRepository

class CarRepositoryImpl (
    private val service: CarService
) : CarRepository {


    override suspend fun getCarList(): List<Car> {
//TODO DANIEL
        val serviceReturn = service.getCarList()
        return serviceReturn.map { carDto ->
            carDto.toCar()
        }
    }
}