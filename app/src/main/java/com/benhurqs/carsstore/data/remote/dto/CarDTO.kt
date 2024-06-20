package com.benhurqs.carsstore.data.remote.dto

import com.benhurqs.carsstore.domain.model.Car
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.text.NumberFormat
import java.util.Locale

@JsonClass(generateAdapter = true)
class CarDTO(
    val cars: List<CarsInformations>
)
class CarsInformations(
    val ano: String?,
    val combustivel: String?,
    val cor: String?,
    val id: Long,
    @Json(name = "modelo_id")
    val modeloId: Int?,
    @Json(name = "marca_nome")
    val marcaNome: String? = null,
    @Json(name = "nome_modelo")
    val nomeModelo: String?,
    @Json(name = "num_portas")
    val numPortas: String?,
    @Json(name = "timestamp_cadastro")
    val timestampCadastro: Long,
    @Json(name = "valor")
    val valorFipe: Double
) {
    fun toCar(): Car {
        return Car(
            ano = ano ?: "",
            combustivel = combustivel ?: "",
            cor = cor ?: "",
            id = id,
            marcaNome = marcaNome ?: "",
            nomeModelo = nomeModelo ?: "",
            numPortas = numPortas ?: "",
            valorFipe = treatmentOfValue(valorFipe)
        )
    }

    private fun treatmentOfValue(valor: Double): String {
        val former = NumberFormat.getCurrencyInstance(Locale("pt", "BR"))

        if (valor < 1000) {
            return former.format(valor * 1000)
        }
        return former.format(valor)
    }
}