package com.example.appelixirgames.model.remote.internet

data class ListaJuegosInternet (

    val id : Int,
    val name : String,
    val released : String,
    val background_image : String,
    val rating : Float,
    val metacritic : Int
)