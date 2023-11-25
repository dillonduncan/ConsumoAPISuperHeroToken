package com.example.consumoapicontoken

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.consumoapicontoken.Models.Superhero
import com.example.consumoapicontoken.Network.API
import com.example.consumoapicontoken.adapters.SuperHeroAdapter
import com.example.consumoapicontoken.adapters.SuperHeroAdapterListener
import com.example.consumoapicontoken.databinding.ActivityMainBinding
import com.example.consumoapicontoken.databinding.ActivityMostrarSuperHeroBinding
import com.example.consumoapicontoken.sharedPreferences.Shared.Companion.prefs
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Mostrar_SuperHero_Activity : AppCompatActivity(), SuperHeroAdapterListener {
    lateinit var binding: ActivityMostrarSuperHeroBinding
    lateinit var adapter: SuperHeroAdapter
    var listSuperHero: MutableList<Superhero> = mutableListOf()
    var listSuperHero2: MutableList<Superhero> = mutableListOf()
    lateinit var token: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMostrarSuperHeroBinding.inflate(layoutInflater)
        setContentView(binding.root)
        token = prefs.obtenerToken()
        getSuperHeroId()
        binding.rvSuperHero.layoutManager = LinearLayoutManager(this)
        llenarRecycle()
    binding.btnRegistrar.setOnClickListener { startActivity(Intent(this,Registrar_SuperHeroes_Activity::class.java)) }
    }

    private fun llenarRecycle() {
        API.buildSuperHero.getSuperHeroAll(token).enqueue(object : Callback<List<Superhero>> {
            override fun onResponse(
                call: Call<List<Superhero>>,
                response: Response<List<Superhero>>
            ) {
                if (response.isSuccessful) {
                    var bodyRespuest = response.body()
                    bodyRespuest?.forEach {
                        listSuperHero.add(it)
                    }
                    adapter = SuperHeroAdapter(listSuperHero, this@Mostrar_SuperHero_Activity)
                    binding.rvSuperHero.adapter = adapter
                }
                listSuperHero2 = listSuperHero
            }

            override fun onFailure(call: Call<List<Superhero>>, t: Throwable) {
                Log.e("Error", t.message.toString())
            }
        })
        listSuperHero = mutableListOf()
    }


    fun getSuperHeroId() {
        binding.apply {
            btnBuscarId.setOnClickListener {
                listSuperHero2.forEach {
                    if(it.ID==edtBuscarPorId.text.toString().toInt()){
                        txtMostrarPorId.text=it.Nombre
                    }
                }
            }
        }
    }

    override fun deleteSuperHero(superhero: Superhero) {
        lifecycleScope.launch {
            API.buildSuperHero.deleteSuperHero(token, superhero.ID)
                .enqueue(object : Callback<Superhero> {
                    override fun onResponse(call: Call<Superhero>, response: Response<Superhero>) {
                        if (response.isSuccessful) {
                            var bodyresponse = response.body()
                            Toast.makeText(
                                this@Mostrar_SuperHero_Activity,
                                "${bodyresponse?.Nombre} fue eliminado",
                                Toast.LENGTH_SHORT
                            ).show()
                            listSuperHero.clear()
                        }
                    }

                    override fun onFailure(call: Call<Superhero>, t: Throwable) {
                        Log.e("ErrorDelete", t.message.toString())
                    }
                })
            adapter.notifyDataSetChanged()
            llenarRecycle()
        }
    }
}