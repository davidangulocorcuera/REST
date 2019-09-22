package david.angulo.apirest.home

import android.arch.lifecycle.ViewModel
import david.angulo.apirest.api.PokeRepository
import javax.inject.Inject

class HomeViewModel @Inject constructor()  : ViewModel() {
    val pokemonResponse = PokeRepository.getAllPosts()
}