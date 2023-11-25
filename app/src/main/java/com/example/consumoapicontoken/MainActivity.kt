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
        binding.btnLogIn.setOnClickListener {
            logIn()
        }
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
                    }else{
                        txtErrorInisio.text="Nombre o contrasenia incorrecto"
                    }
                }

                override fun onFailure(call: Call<LoginResponce>, t: Throwable) {
                    Log.e("Error", t.message.toString())
                }
            })
        }
    }
}