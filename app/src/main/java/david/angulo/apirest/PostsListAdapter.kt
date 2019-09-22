package david.angulo.apirest

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import david.angulo.apirest.model.Post
import kotlinx.android.synthetic.main.item_post.view.*

class PostsListAdapter( var posts: ArrayList<Post> = ArrayList(), var onClick: (Post) -> Unit) :
    RecyclerView.Adapter<PostsListAdapter.PostViewHolder>() {

    inner class PostViewHolder(view: View) : RecyclerView.ViewHolder(view)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_post, parent, false)

        return PostViewHolder(view)
    }

    override fun getItemCount() = posts.size

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {

        val current = posts[position]

        holder.itemView.postsTitle.text = current.title
        holder.itemView.postsContent.text = current.body
        holder.itemView.setOnClickListener {
            onClick(current)
        }
    }

}