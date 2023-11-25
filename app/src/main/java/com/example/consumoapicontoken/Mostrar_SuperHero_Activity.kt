package com.example.consumoapicontoken

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.consumoapicontoken.Models.Superhero
import com.example.consumoapicontoken.Network.API
import com.example.consumoapicontoken.databinding.ActivityMainBinding
import com.example.consumoapicontoken.databinding.ActivityMostrarSuperHeroBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Mostrar_SuperHero_Activity : AppCompatActivity() {
    lateinit var binding: ActivityMostrarSuperHeroBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMostrarSuperHeroBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getSuperHeroId()
    }

    fun getSuperHeroId() {
        binding.apply {
            btnBuscarId.setOnClickListener {
                API.buildSuperHero.getSuperHeroId(edtBuscarPorId.text.toString().toInt())
                    .enqueue(object : Callback<Superhero> {
                        override fun onResponse(
                            call: Call<Superhero>,
                            response: Response<Superhero>
                        ) {
                            if (response.isSuccessful) {
                                var a = response.body()
//                                txtMostrarPorId.text =
//                                    "${a?.ID} / ${a?.Nombre} / ${a?.AnioDebut} / ${a?.PlanetaOrigenASuperHero}"
                                txtMostrarPorId.text =
                                    " ${a?.Nombre} "
                            } else {
                                Toast.makeText(
                                    this@Mostrar_SuperHero_Activity,
                                    "Vales webo",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }

                        override fun onFailure(call: Call<Superhero>, t: Throwable) {
                            Log.e("Error", t.message.toString())
                        }
                    })
            }
            API.buildSuperHero.getSuperHeroId(1).enqueue(object : Callback<Superhero> {
                override fun onResponse(call: Call<Superhero>, response: Response<Superhero>) {
                    var a=response.body()
                    btnBuscarId.text=a?.Nombre
                }

                override fun onFailure(call: Call<Superhero>, t: Throwable) {
                    Log.e("Error", t.message.toString())
                }
            })
        }
    }
}