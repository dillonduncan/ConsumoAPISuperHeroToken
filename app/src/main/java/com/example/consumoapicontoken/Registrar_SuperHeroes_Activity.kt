package com.example.consumoapicontoken

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.consumoapicontoken.Models.Superhero
import com.example.consumoapicontoken.Network.API
import com.example.consumoapicontoken.databinding.ActivityRegistrarSuperHeroesBinding
import com.example.consumoapicontoken.sharedPreferences.Shared.Companion.prefs
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Registrar_SuperHeroes_Activity : AppCompatActivity() {
    lateinit var binding: ActivityRegistrarSuperHeroesBinding
    lateinit var token: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrarSuperHeroesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        token = prefs.obtenerToken()
        RegistrarSuperHero()
    }

    fun RegistrarSuperHero() {
        binding.apply {
            btnRegistrar.setOnClickListener {
                if (edtNombre.text!!.isNotEmpty() && edtAnioDebut.text!!.isNotEmpty() && edtContrasenia.text!!.isNotEmpty() && edtplanetaOrigen.text!!.isNotEmpty()) {
                    var sph = Superhero(
                        0,
                        edtNombre.text.toString(),
                        edtAnioDebut.text.toString().toInt(),
                        edtContrasenia.text.toString(),
                        edtplanetaOrigen.text.toString()
                    )
                    API.buildSuperHero.postSuperHero(token, sph)
                        .enqueue(object : Callback<Superhero> {
                            override fun onResponse(
                                call: Call<Superhero>,
                                response: Response<Superhero>
                            ) {
                                if (response.isSuccessful) {
                                    Toast.makeText(
                                        this@Registrar_SuperHeroes_Activity,
                                        "${response.body()?.Nombre} registrado",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                } else {
                                    Toast.makeText(
                                        this@Registrar_SuperHeroes_Activity,
                                        "Hubo un error al ejecutar la accion",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }

                            override fun onFailure(call: Call<Superhero>, t: Throwable) {
                                Log.e("ErrorRegistrar", t.message.toString())
                            }
                        })
                }else{
                    Toast.makeText(this@Registrar_SuperHeroes_Activity, "Llene todos los campos", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

}