package david.angulo.apirest.home

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import david.angulo.apirest.model.Post
import david.angulo.apirest.api.PostsRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel() {
    private val posts = MutableLiveData<ArrayList<Post>>()

    val postsResponse: MutableLiveData<ArrayList<Post>> by lazy {
        MutableLiveData<ArrayList<Post>>().also {
            posts
        }
    }

    fun getAllPosts() {
        PostsRepository.initializeRetrofit().getAllResults().enqueue(object : Callback<List<Post>> {
            override fun onFailure(call: Call<List<Post>>?, t: Throwable?) {
                Log.v("taag", t.toString())
            }

            override fun onResponse(call: Call<List<Post>>?, response: Response<List<Post>>?) {
                response?.apply {
                    postsResponse.postValue(ArrayList(body()))
                }
            }
        })
    }

}