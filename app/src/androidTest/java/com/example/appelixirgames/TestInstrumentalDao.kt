package com.example.appelixirgames

import androidx.lifecycle.Observer
import androidx.room.Room
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.internal.runner.junit4.statement.UiThreadStatement
import androidx.test.platform.app.InstrumentationRegistry
import com.example.appelixirgames.model.local.DaoAppElixir
import com.example.appelixirgames.model.local.dataBase.DataBaseAppElixir
import com.example.appelixirgames.model.local.entities.ListaJuegosLocal
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class TestInstrumentalDao {

    //Variables de prueba
    private lateinit var db: DataBaseAppElixir
    private lateinit var elixirAppDao: DaoAppElixir


    @Before
    fun setUp() {
        //Inicialización de la base de datos y el DAO
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        db = Room.inMemoryDatabaseBuilder(context, DataBaseAppElixir::class.java).build()
        elixirAppDao = db.getDaoAppElixir()
    }


    @After
    fun tearDown() {
        //Cierre de la base de datos
        db.close()
    }


    @Test
    fun testCrudOperacion() = runBlocking {
        //Creación datos de prueba
        val listaJuegos = listOf(
            ListaJuegosLocal(1, "Tumblepop", "1980-08-09", "img1", 4.5f, 100),
            ListaJuegosLocal(2, "Mario Kart", "2000-02-02", "img2", 4.0f, 250)
        )

        //Insertar los datos de prueba en la base de datos
        elixirAppDao.insertarListaJuegos(listaJuegos)

        //Observar LiveData en el hilo principal
        UiThreadStatement.runOnUiThread {
            //Obtener LiveData de todas los juegos
            val todosLosJuegosLD = elixirAppDao.obtenerListadoJuegos()

            //Crear el observador de la lista de juegos
            val observadorJuegos = Observer<List<ListaJuegosLocal>> { listadoJuegos ->
                ViewMatchers.assertThat(listadoJuegos, CoreMatchers.not(emptyList()))

                Assert.assertEquals(2, listadoJuegos.size)
            }

            //Observar los juegos
            todosLosJuegosLD.observeForever(observadorJuegos)
            // Quitar el observador después de realizar las aserciones
            todosLosJuegosLD.removeObserver(observadorJuegos)
        }
    }
}