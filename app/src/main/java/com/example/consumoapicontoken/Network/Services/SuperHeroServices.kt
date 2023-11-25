package com.example.consumoapicontoken.Network.Services

import com.example.consumoapicontoken.Models.Superhero
import com.example.consumoapicontoken.Network.responce.LoginResponce
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface SuperHeroServices {
    @GET("SuperHeroe")
    fun getSuperHeroAll(@Header("Authorization") token: String): Call<List<Superhero>>

    @GET("SuperHeroe/{id}")
    fun getSuperHeroId(@Header("Authorization") token: String, @Path("id") id: Int): Call<Superhero>

    @POST("SuperHeroe")
    fun postSuperHero(
        @Header("Authorization") token: String,
        @Body superhero: Superhero
    ): Call<Superhero>

    @DELETE("SuperHeroe/{id}")
    fun deleteSuperHero(
        @Header("Authorization") token: String,
        @Path("id") id: Int
    ): Call<Superhero>
}