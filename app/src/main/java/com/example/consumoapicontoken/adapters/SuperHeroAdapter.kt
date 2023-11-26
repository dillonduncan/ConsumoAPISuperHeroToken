package com.example.consumoapicontoken.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.consumoapicontoken.Models.Superhero
import com.example.consumoapicontoken.R

class SuperHeroAdapter(
    val listaSuperHero: MutableList<Superhero>,
    val listener: SuperHeroAdapterListener
) : RecyclerView.Adapter<SuperHeroAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var vista = LayoutInflater.from(parent.context)
            .inflate(R.layout.ly_mostrat_superhero, parent, false)
        return ViewHolder(vista)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val superhero = listaSuperHero[position]
        holder.txtidSH.text ="ID: "+ superhero.ID.toString()
        holder.txtNombreSH.text = "Nombre: "+superhero.Nombre
        holder.txtAnioDebut.text ="Año de debut: "+ superhero.AnioDebut.toString()
        holder.txtPlanetaOrigen.text ="Planeta de origen: "+ superhero.PlanetaOrigenASuperHero
    }

    override fun getItemCount(): Int = listaSuperHero.size

    inner class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val txtidSH = itemView.findViewById<TextView>(R.id.txtIdSH)
        val txtNombreSH = itemView.findViewById<TextView>(R.id.txtNombreSH)
        val txtAnioDebut = itemView.findViewById<TextView>(R.id.txtDebutAnio)
        val txtPlanetaOrigen = itemView.findViewById<TextView>(R.id.txtPlanetaOrigen)
       // val btnBorrar = itemView.findViewById<Button>(R.id.btnBorrarSH)
    }
}