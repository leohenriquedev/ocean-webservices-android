package com.example.ocean_android_webservices

import retrofit2.Call
import retrofit2.http.GET

interface PokemonService {
    @GET("/pokemon")
    fun listPokemons(): Call<PokemonApiResult>
}