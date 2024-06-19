package com.benhurqs.carsstore.domain.model

import java.io.Serializable

data class Car(
    val ano: String,
    val combustivel: String,
    val cor: String,
    val id: Long,
    val marcaNome: String,
    val nomeModelo: String,
    val numPortas: String,
    val valorFipe: String
) : Serializable