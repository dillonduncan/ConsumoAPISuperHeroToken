package com.example.consumoapicontoken.sharedPreferences

import android.content.Context

class SharedPreferences(context: Context) {
    val storage=context.getSharedPreferences("SP", 0)
    fun GuardarTokenSH(token:String){
        storage.edit().putString("Token",token).apply()
    }
    fun obtenerToken():String{
        return storage.getString("Token","No se encontro token").toString()
    }
    fun Borrar(){
        storage.edit().clear()
    }
}