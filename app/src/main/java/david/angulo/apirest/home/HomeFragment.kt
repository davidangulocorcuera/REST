package david.angulo.apirest.home


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import david.angulo.apirest.PokemonsListAdapter
import david.angulo.apirest.R
import david.angulo.apirest.model.Pokemon
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment() {

    private lateinit var postsAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    private var pokemons: ArrayList<Pokemon> = ArrayList()
    private val homeViewModel: HomeViewModel by lazy {
        ViewModelProviders.of(this).get(HomeViewModel()::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initList()
        initializeViewModel()

    }

    private fun initList() {
        viewManager = LinearLayoutManager(context)
        context?.let { context ->
            postsAdapter = PokemonsListAdapter(pokemons, context){ post ->
                view?.let{
                    showSnackbar(it,post.name)
                }
            }
        }
        rvPosts.apply {
            layoutManager = viewManager
            adapter = postsAdapter
        }
    }

    private fun initializeViewModel() {
        homeViewModel.pokemonResponse.observe(this, Observer { pokemonResponse ->
            pokemonResponse?.let {
                this.pokemons.clear()
                this.pokemons.addAll(it.results)
                postsAdapter.notifyDataSetChanged()
            }
        })

    }

    private fun showSnackbar(view: View, text: String) {
        context?.let { context ->
            val snackbar = Snackbar.make(view, text, Snackbar.LENGTH_LONG)
            snackbar.view.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary))
            snackbar.setActionTextColor(ContextCompat.getColor(context, R.color.colorWhite))
            snackbar.show()
        }
    }

    private fun openWebView(url: String){
        val webView = WebView(context)
        webView.loadUrl(url)

    }

}
