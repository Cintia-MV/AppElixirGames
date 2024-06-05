package com.example.appelixirgames.model.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tabla_lista_juegos")
data class ListaJuegosLocal(

    @PrimaryKey
    val id : Int,
    val name : String,
    val released : String,
    val background_image : String,
    val rating : Float,
    val metacritic : Int
)
