package com.example.ocean_android_webservices

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tvResult = findViewById<TextView>(R.id.tvResult)
        val imgPokemon = findViewById<ImageView>(R.id.imgPokemon)

//        val url = "";
//        Glide.with(this).load(url).into(imgPokemon)

        tvResult.text = "Loading Pokemon's List...";

        val retrofit = Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(PokemonService::class.java)

        val call = service.listPokemons()

        call.enqueue(object : Callback<PokemonApiResult> {
            override fun onResponse(
                call: Call<PokemonApiResult>,
                response: Response<PokemonApiResult>
            ) {
                if(response.isSuccessful) {
                    val body = response.body()

                    body?.results?.let { pokemons ->
                        tvResult.text = ""
                        pokemons.forEach { pokemon ->
                            tvResult.append(pokemon.name + "\n")
                        }
                    }
                }
            }

            override fun onFailure(call: Call<PokemonApiResult>, t: Throwable) {
                tvResult.text = "Error to loading Pokemon's list!"
                Log.e("MAIN_ACTIVITY", "Error to call API", t)
            }

        })
    }
}