package com.example.appelixirgames.model.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tabla_detalle_juego")
data class DetalleJuegoLocal(

    @PrimaryKey
    val id: Int,
    val name: String,
    val released: String,
    val background_image : String,
    val rating : Float,
    val metacritic : Int
)
