package com.example.appelixirgames.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.appelixirgames.AdapterAppElixir
import com.example.appelixirgames.R
import com.example.appelixirgames.databinding.FragmentFirstBinding
import com.example.appelixirgames.viewModel.ViewModelAppElixir


class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!

    //ViewModel
    private val viewModel : ViewModelAppElixir by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Instancia del adaptador
        val adaptador = AdapterAppElixir()
        binding.recyclerView.adapter = adaptador
        binding.recyclerView.layoutManager = LinearLayoutManager(context)

        viewModel.obtenerListaJuegos().observe(viewLifecycleOwner, Observer {
            it?.let{
                Log.d("Listado", it.toString())
                adaptador.actualizar(it)
            }
        })

        //Metodo para seleccionar un juego
        adaptador.selccionarJuego().observe(viewLifecycleOwner, Observer {
            it.let {
                Log.d("seleccion", it.toString())
            }
            val bundle = Bundle().apply {
                putInt("idJuego", it.id)
            }
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment, bundle)
        })




    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}