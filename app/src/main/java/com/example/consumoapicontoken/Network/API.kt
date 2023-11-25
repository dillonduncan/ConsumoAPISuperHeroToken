package com.example.consumoapicontoken.Network

import com.example.consumoapicontoken.Network.Services.LoginServices
import com.example.consumoapicontoken.Network.Services.SuperHeroServices
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object API {
    private var build = Retrofit.Builder().baseUrl("http://192.168.100.12:8200/api/")
        .addConverterFactory(GsonConverterFactory.create()
        ).build()
    var token="aqui va el token"
    var buildLogin= build.create(LoginServices::class.java)
    var buildSuperHero= build.create(SuperHeroServices::class.java)

}