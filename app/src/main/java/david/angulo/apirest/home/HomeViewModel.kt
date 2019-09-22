package david.angulo.apirest.home

import android.arch.lifecycle.ViewModel
import david.angulo.apirest.api.PostsRepository
import javax.inject.Inject

class HomeViewModel @Inject constructor()  : ViewModel() {
    val posts = PostsRepository.getAllPosts()
}