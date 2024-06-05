package com.example.appelixirgames.model.remote

import com.example.appelixirgames.model.remote.internet.DetalleJuegoInternet
import com.example.appelixirgames.model.remote.internet.ListaJuegosInternet
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiElixirApp {

    @GET("games")
    suspend fun fetchJuegosList(): Response<List<ListaJuegosInternet>>

    @GET("games/{id}")
    suspend fun fetchDetailJuego(@Path("id") id: Int): Response<DetalleJuegoInternet>
}