package com.example.consumoapicontoken.Network.Services

import com.example.consumoapicontoken.Models.LoginSuperHero
import com.example.consumoapicontoken.Network.responce.LoginResponce
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginServices {
    @POST("login")
    fun postLogin(@Body loginSuperHero: LoginSuperHero): Call<LoginResponce>
}