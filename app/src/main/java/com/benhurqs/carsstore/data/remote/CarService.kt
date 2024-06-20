package com.benhurqs.carsstore.data.remote

import com.benhurqs.carsstore.data.remote.dto.CarDTO
import com.benhurqs.carsstore.data.remote.dto.LeadRequest
import com.benhurqs.carsstore.domain.model.Lead
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface CarService {
    @GET("/cars.json")
    suspend fun getCarList(): CarDTO

    @POST("cars/leads")
    suspend fun sendLead(
        @Body leads: List<Lead>
    ): Response<LeadRequest>
}