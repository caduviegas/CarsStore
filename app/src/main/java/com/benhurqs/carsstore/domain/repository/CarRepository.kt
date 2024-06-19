package com.benhurqs.carsstore.domain.repository

import com.benhurqs.carsstore.domain.model.Car

interface CarRepository {
    suspend fun getCarList(): List<Car>
}