package david.angulo.apirest.home

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import david.angulo.apirest.api.PostsRepository
import david.angulo.apirest.model.Post

class HomeViewModel  : ViewModel() {
    val posts = PostsRepository.getAllPosts()


}