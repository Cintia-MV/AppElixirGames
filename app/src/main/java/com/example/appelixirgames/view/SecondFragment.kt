package com.example.appelixirgames.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.appelixirgames.R
import com.example.appelixirgames.databinding.FragmentSecondBinding
import com.example.appelixirgames.viewModel.ViewModelAppElixir


class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding!!

    //ViewModel
    private val viewModel : ViewModelAppElixir by activityViewModels()

    private var idJuego : Int? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let { bundle ->
            idJuego = bundle.getInt("idJuego")
            Log.d("Seleccion fragment", idJuego.toString())

        }

        idJuego?.let { id->
            viewModel.obtenerDetalleInternet(id)
        }


        viewModel.obtenerDetalleJuego().observe(viewLifecycleOwner, Observer {
            Log.d("Seleccion dos", idJuego.toString())

            var idGame = it.id
            var nombre = it.name

            //Cargar datos desde la seleccion
            Glide.with(binding.imgJuego2).load(it.background_image).into(binding.imgJuego2)
            binding.tvName.text = it.name
            binding.tvDate.text = it.released
            binding.tvMetacritic.text = "Metacritic: ${it.metacritic.toString()}"
            binding.ratingBar.rating = it.rating



            /*************ENVIAR EMAIL**********/

            binding.fab.setOnClickListener {

                //Inicializo intent
                val intent = Intent(Intent.ACTION_SEND)

                intent.data = Uri.parse("mailto")
                intent.type = "text/plain"


                //Enviar correo a:
                intent.putExtra(Intent.EXTRA_EMAIL, arrayOf("ventas@elixirgames.cl"))

                //Asunto del correo
                intent.putExtra(
                    Intent.EXTRA_SUBJECT,
                    "Consulta por Juego " + nombre + " id " +idGame

                )

                //Mensaje del correo
                intent.putExtra(
                    Intent.EXTRA_TEXT, "Hola\n " +
                            "Vi el juego "+nombre+" de código "+idGame+" y me gustaría que me contactaran " +
                            "a este correo o al siguiente número _________ " +
                            "Quedo atento"

                )

                try {
                    startActivity(intent)
                }catch (e: Exception){
                    Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
                }

            }



        })



    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}