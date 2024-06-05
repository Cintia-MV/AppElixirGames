package com.example.appelixirgames.model

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.appelixirgames.model.local.DaoAppElixir
import com.example.appelixirgames.model.local.entities.DetalleJuegoLocal
import com.example.appelixirgames.model.local.entities.ListaJuegosLocal
import com.example.appelixirgames.model.remote.RetrofitAppElixir

class RepoAppElixir(private val daoAppElixir: DaoAppElixir) {
    //Retrofit
    private val networkService = RetrofitAppElixir.getRetrofit()

    //Lista de juegos Dao
    val listaJuegosRepo = daoAppElixir.obtenerListadoJuegos()

    //Detalle de un juego
    val detalleJuegoLocal = MutableLiveData<DetalleJuegoLocal>()

    //Listado
    val listaJuegoLocal = MutableLiveData<ListaJuegosLocal>()

    suspend fun fetchJuegos(){
        val service = kotlin.runCatching { networkService.fetchJuegosList() }

        service.onSuccess {
            when (it.code()) {
                in 200..299 -> it.body()?.let {
                    Log.d("Cursos", it.toString())
                    daoAppElixir.insertarListaJuegos(juegosInternet(it))
                }

                else -> Log.d("Repo", "${it.code()}-${it.errorBody()}")
            }
            service.onFailure {
                Log.d("ERROR", "${it.message}")
            }

        }

    }


    suspend fun fetchDetalleJuego(id: Int): DetalleJuegoLocal?{
        val service = kotlin.runCatching { networkService.fetchDetailJuego(id)}

        return service.getOrNull()?.body()?.let{juego ->
            val detalleJuegoEntity = detalleInternet(juego)
            daoAppElixir.insertarDetalleJuego(detalleJuegoEntity)
            detalleJuegoEntity

        }
    }

}