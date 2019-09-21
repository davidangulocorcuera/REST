package david.angulo.apirest.api

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import david.angulo.apirest.model.Post
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Singleton
object PostsRepository {
    private lateinit var service: ApiService


   private fun initializeRetrofit(): ApiService {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create<ApiService>(ApiService::class.java)
    }

    fun getAllPosts(): LiveData<List<Post>> {
        val data = MutableLiveData<List<Post>>()
            initializeRetrofit().getAllResults().enqueue(object : Callback<List<Post>> {
            override fun onFailure(call: Call<List<Post>>?, t: Throwable?) {
                Log.v("taag", t.toString())
            }

            override fun onResponse(call: Call<List<Post>>?, response: Response<List<Post>>?) {
                response?.apply {
                    data.value = body()
                }
            }
        })
        return data
    }

}
