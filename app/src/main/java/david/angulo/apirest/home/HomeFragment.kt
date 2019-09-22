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
import david.angulo.apirest.PostsListAdapter
import david.angulo.apirest.R
import david.angulo.apirest.model.Post
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment() {

    private lateinit var postsAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    private var posts: ArrayList<Post> = ArrayList()
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
        postsAdapter = PostsListAdapter(posts){post ->
            view?.let{
                showSnackbar(it,post.title)
            }
        }
        rvPosts.apply {
            layoutManager = viewManager
            adapter = postsAdapter
        }
    }

    private fun initializeViewModel() {
        homeViewModel.posts.observe(this, Observer { posts ->
            posts?.let {
                this.posts.clear()
                this.posts.addAll(it)
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

}
