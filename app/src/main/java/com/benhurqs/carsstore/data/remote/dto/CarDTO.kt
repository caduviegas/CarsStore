package com.benhurqs.carsstore.data.remote.dto

import com.benhurqs.carsstore.domain.model.Car
import com.squareup.moshi.Json
import java.text.NumberFormat
import java.util.Locale

class CarDTO (
    val cars: Array<CarsInformations>
)

class CarsInformations(
    val ano: String?,
    val combustivel: String?,
    val cor: String?,
    val id: Long,
    @Json(name = "marca_id")
    val marcaId: Int,
    @Json(name = "marca_nome")
    val marcaNome: String?,
    @Json(name = "nome_modelo")
    val nomeModelo: String?,
    @Json(name = "num_portas")
    val numPortas: String?,
    @Json(name = "timestamp_cadastro")
    val timestampCadastro: String,
    @Json(name = "valor_fipe")
    val valorFipe: String
){

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

    private fun treatmentOfValue(valor: String): String {

        val former = NumberFormat.getCurrencyInstance(Locale("pt", "br"))
        val convertValue = valor.toDouble()

        if (convertValue < 1000) {
            return former.format(convertValue * 1000).toString()
        }
        return former.format(convertValue).toString()
    }
}
