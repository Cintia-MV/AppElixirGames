package com.example.appelixirgames.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.appelixirgames.model.RepoAppElixir
import com.example.appelixirgames.model.local.dataBase.DataBaseAppElixir
import com.example.appelixirgames.model.local.entities.DetalleJuegoLocal
import com.example.appelixirgames.model.local.entities.ListaJuegosLocal
import kotlinx.coroutines.launch

class ViewModelAppElixir (aplicacion: Application): AndroidViewModel(aplicacion){
    //Conexión con el repo
    private val repo : RepoAppElixir

    //Entidad
    private val detalleJuegoLocal = MutableLiveData<DetalleJuegoLocal>()

    init {
        val bd = DataBaseAppElixir.getDataBase(aplicacion)
        val juegoAppDao = bd.getDaoAppElixir()
        repo = RepoAppElixir(juegoAppDao)

        // Llamar fetch lista
        viewModelScope.launch {
            repo.fetchJuegos()
        }
    }

    //Listado de elementos
    fun obtenerListaJuegos(): LiveData<List<ListaJuegosLocal>> = repo.listaJuegosRepo


    //Detalle de las plantas
    fun obtenerDetalleJuego(): LiveData<DetalleJuegoLocal> = detalleJuegoLocal


    fun obtenerDetalleInternet(id:Int) = viewModelScope.launch {
        val detalleJuego = repo.fetchDetalleJuego(id)
        detalleJuego?.let {
            detalleJuegoLocal.postValue(it)
        }
    }

}