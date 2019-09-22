package david.angulo.apirest.api

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import david.angulo.apirest.model.Pokemon
import david.angulo.apirest.model.PokemonResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Singleton
object PokeRepository {
    private lateinit var service: ApiService


    private fun initializeRetrofit(): ApiService {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create<ApiService>(ApiService::class.java)
    }

    fun getAllPosts(): LiveData<PokemonResponse> {
        val data = MutableLiveData<PokemonResponse>()
        initializeRetrofit().getAllPokemons().enqueue(object : Callback<PokemonResponse> {
            override fun onFailure(call: Call<PokemonResponse>?, t: Throwable?) {
                Log.v("taag", t.toString())
            }


            override fun onResponse(
                call: Call<PokemonResponse>?,
                response: Response<PokemonResponse>?
            ) {
                response?.apply {
                    data.value = body()
                }
            }
        })
        return data
    }

}
