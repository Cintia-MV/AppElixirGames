package com.example.appelixirgames.model.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.appelixirgames.model.local.entities.DetalleJuegoLocal
import com.example.appelixirgames.model.local.entities.ListaJuegosLocal

@Dao
interface DaoAppElixir {
    //Insertar lista de juegos
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarListaJuegos(listaJuegos: List<ListaJuegosLocal>)

    //Seleccionar listado de juegos
    @Query("SELECT * FROM tabla_lista_juegos ORDER BY id ASC")
    fun obtenerListadoJuegos(): LiveData<List<ListaJuegosLocal>>

    //Insertar detalle juego
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarDetalleJuego(juego: DetalleJuegoLocal)

    //Seleccionar detalle de juego
    @Query("SELECT * FROM tabla_detalle_juego WHERE id= :id")
    fun obtenerDetalleJuego(id: Int): LiveData<DetalleJuegoLocal>
}