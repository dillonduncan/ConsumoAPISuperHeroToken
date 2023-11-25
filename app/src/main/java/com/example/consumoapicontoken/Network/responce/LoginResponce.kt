package com.example.consumoapicontoken.Network.responce

data class LoginResponce(
    val DateTime: String,
    val SuperHero: SuperHeroRespoce,
    val Token: String,
    val status:Boolean
)