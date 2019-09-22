package david.angulo.apirest.api

import david.angulo.apirest.model.Pokemon
import david.angulo.apirest.model.PokemonResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @GET("pokemon")
    fun getAllPokemons(): Call<PokemonResponse>
}