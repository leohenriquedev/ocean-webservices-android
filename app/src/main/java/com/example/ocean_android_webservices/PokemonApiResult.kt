package com.example.ocean_android_webservices

data class PokemonApiResult(
    val count: Int,
    val next: String,
    val previous: String,
    val results: List<PokemonResult>
)

data class PokemonResult(
    val name: String,
    val url: String
)