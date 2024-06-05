package com.example.appelixirgames.model.local.dataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.appelixirgames.model.local.DaoAppElixir
import com.example.appelixirgames.model.local.entities.DetalleJuegoLocal
import com.example.appelixirgames.model.local.entities.ListaJuegosLocal

@Database(entities = [DetalleJuegoLocal::class, ListaJuegosLocal::class], version = 1, exportSchema = false)
abstract class DataBaseAppElixir :RoomDatabase(){

    abstract fun getDaoAppElixir() : DaoAppElixir

    companion object{
        @Volatile
        private var INSTANCE: DataBaseAppElixir? = null

        fun getDataBase(context: Context): DataBaseAppElixir{
            val temInstance = INSTANCE
            if(temInstance!= null){
                return temInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DataBaseAppElixir::class.java,
                    "elixir_db"
                )
                    .build()
                INSTANCE= instance
                return instance
            }
        }
    }

}