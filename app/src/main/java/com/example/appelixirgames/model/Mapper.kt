package com.example.appelixirgames.model

import com.example.appelixirgames.model.local.entities.DetalleJuegoLocal
import com.example.appelixirgames.model.local.entities.ListaJuegosLocal
import com.example.appelixirgames.model.remote.internet.DetalleJuegoInternet
import com.example.appelixirgames.model.remote.internet.ListaJuegosInternet

fun juegosInternet(listaJuegos: List<ListaJuegosInternet>): List<ListaJuegosLocal>{
    return listaJuegos.map {
        ListaJuegosLocal(
            id = it.id,
            name = it.name,
            released = it.released,
            background_image = it.background_image,
            rating = it.rating,
            metacritic = it.metacritic
        )
    }
}

fun detalleInternet(detalle: DetalleJuegoInternet): DetalleJuegoLocal{
    return DetalleJuegoLocal(
        id = detalle.id,
        name = detalle.name,
        released = detalle.released,
        background_image = detalle.background_image,
        rating = detalle.rating,
        metacritic = detalle.metacritic
    )
}