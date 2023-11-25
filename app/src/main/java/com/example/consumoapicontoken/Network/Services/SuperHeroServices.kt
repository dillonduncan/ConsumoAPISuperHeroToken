package com.example.consumoapicontoken.Network.Services

import com.example.consumoapicontoken.Models.Superhero
import com.example.consumoapicontoken.Network.responce.LoginResponce
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface SuperHeroServices {
    @GET("SuperHeroe")
    fun getSuperHeroAll(): Call<List<Superhero>>

    @GET("SuperHeroe/{id}")
    fun getSuperHeroId(@Path("id") id: Int): Call<Superhero>
}