package david.angulo.apirest.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object PostsRepository {
    private lateinit var service: ApiService


    fun initializeRetrofit(): ApiService {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create<ApiService>(ApiService::class.java)
    }

}