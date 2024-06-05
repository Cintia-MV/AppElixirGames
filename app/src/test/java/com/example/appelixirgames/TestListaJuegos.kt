package com.example.appelixirgames

import com.example.appelixirgames.model.local.entities.ListaJuegosLocal
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class TestListaJuegos {

    //Variable lateinit para la entidad
    private lateinit var listaJuegos : ListaJuegosLocal

    //Funcion que se ejecuta antes de cada prueba
    @Before
    fun setUp(){
        //Entidad inicializada con valores de prueba
        listaJuegos = ListaJuegosLocal(
            id = 1,
            name = "Mario Kart",
            released = "2000-02-02",
            background_image = "img",
            rating = 4.8f,
            metacritic = 30

        )
    }

    // Función que se ejecuta después de cada prueba
    @After
    fun tearDown(){

    }

    // Función de prueba para verificar el constructor de la entidad
    @Test
    fun testListaJuegos(){
        assert(listaJuegos.id == 1)
        assert(listaJuegos.name == "Mario Kart")
        assert(listaJuegos.released == "2000-02-02")
        assert(listaJuegos.background_image == "img")
        assert(listaJuegos.rating == 4.8f)
        assert(listaJuegos.metacritic == 30)
    }

}