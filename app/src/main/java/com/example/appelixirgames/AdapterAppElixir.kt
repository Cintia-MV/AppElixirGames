package com.example.appelixirgames

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.appelixirgames.databinding.JuegosListBinding
import com.example.appelixirgames.model.local.entities.ListaJuegosLocal

class AdapterAppElixir : RecyclerView.Adapter<AdapterAppElixir.ListaJuegosVH>(){

    //Referencias para el adaptador
    private var listaJuegos = listOf<ListaJuegosLocal>()
    private val juegoSeleccionado = MutableLiveData<ListaJuegosLocal>()

    //Función para actualizar el adapter
    fun actualizar(juego: List<ListaJuegosLocal>){
        listaJuegos = juego
        notifyDataSetChanged()
    }


    //Función para seleccionar un juego
    fun selccionarJuego(): LiveData<ListaJuegosLocal> = juegoSeleccionado



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListaJuegosVH {
        return ListaJuegosVH(JuegosListBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun getItemCount() = listaJuegos.size

    override fun onBindViewHolder(holder: ListaJuegosVH, position: Int) {
        var juego = listaJuegos[position]
        holder.bind(juego)
    }


    //Clase interna
    inner class ListaJuegosVH(private val binding: JuegosListBinding):
            RecyclerView.ViewHolder(binding.root), View.OnClickListener{

                fun bind(juego: ListaJuegosLocal){
                    Glide.with(binding.imgJuego).load(juego.background_image).centerCrop().override(300, 500).into(binding.imgJuego)
                    binding.textName.text = juego.name
                    binding.textDate.text = juego.released

                    itemView.setOnClickListener(this)
                }




        override fun onClick(v: View?) {
            juegoSeleccionado.value = listaJuegos[adapterPosition]
        }

    }




}