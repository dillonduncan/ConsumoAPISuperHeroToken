package com.example.consumoapicontoken

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.consumoapicontoken.Models.LoginSuperHero
import com.example.consumoapicontoken.Models.Superhero
import com.example.consumoapicontoken.Network.API
import com.example.consumoapicontoken.Network.responce.LoginResponce
import com.example.consumoapicontoken.databinding.ActivityMainBinding
import com.example.consumoapicontoken.sharedPreferences.Shared.Companion.prefs
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.math.log

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        binding.apply {
//            btnLogIn.setOnClickListener {
//                var spLogIn = LoginSuperHero(edtName.text.toString(), edtPassword.text.toString())
//                postLogin(spLogIn)
//            }
//        }
//        getSuperHeroes()
        binding.btnLogIn.setOnClickListener {
            logIn()
        }
        //getSuperHeros()
    }

    fun getSuperHeros(){
        API.buildSuperHero.getSuperHeroAll().enqueue(object : Callback<List<Superhero>> {
            override fun onResponse(
                call: Call<List<Superhero>>,
                response: Response<List<Superhero>>
            ) {
                var a=response.body()
                a?.forEach {
                    binding.txt.append(it.Nombre)
                }
            }

            override fun onFailure(call: Call<List<Superhero>>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }
    fun logIn() {
        binding.apply {
            var spLogIn = LoginSuperHero(edtName.text.toString(), edtPassword.text.toString())
            API.buildLogin.postLogin(spLogIn).enqueue(object : Callback<LoginResponce> {
                override fun onResponse(
                    call: Call<LoginResponce>,
                    response: Response<LoginResponce>
                ) {
                    if (response.isSuccessful) {
                        startActivity(
                            Intent(
                                this@MainActivity,
                                Mostrar_SuperHero_Activity::class.java
                            )
                        )
                        prefs.GuardarTokenSH(response.body()?.Token.toString())
                        Log.e("response", response.body()?.Token.toString())
                    }
                }

                override fun onFailure(call: Call<LoginResponce>, t: Throwable) {
                    Log.e("Error", t.message.toString())
                }
            })
        }
    }

    fun getSuperHeroes() {
        API.buildSuperHero.getSuperHeroAll().enqueue(object : Callback<List<Superhero>> {
            override fun onResponse(
                call: Call<List<Superhero>>,
                response: Response<List<Superhero>>
            ) {
                //if (response.isSuccessful) {
                var a = response.body()
                binding.txt.text = a?.size.toString()
                a?.forEach {
                    binding.txt.append("${it.Nombre}\n")
                }
                //  }
                // else{
                // Toast.makeText(this@MainActivity, "sfdsfs", Toast.LENGTH_SHORT).show()
                //}
            }

            override fun onFailure(call: Call<List<Superhero>>, t: Throwable) {
                Log.e("Error", t.message.toString())
            }

        })
    }

    fun postLogin(body: LoginSuperHero) {
        API.buildLogin.postLogin(body).enqueue(object : Callback<LoginResponce> {
            override fun onResponse(
                call: Call<LoginResponce>,
                response: Response<LoginResponce>
            ) {
                var respuest = response.body()
                if (respuest?.status == true) {
                    startActivity(Intent(this@MainActivity, Mostrar_SuperHero_Activity::class.java))
                }
                binding.txt.text = respuest?.Token
            }

            override fun onFailure(call: Call<LoginResponce>, t: Throwable) {
                Log.e("Error", t.message.toString())
            }
        })
    }
}