package david.angulo.apirest

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import david.angulo.apirest.model.Pokemon
import kotlinx.android.synthetic.main.item_post.view.*

class PokemonsListAdapter(var pokemons: ArrayList<Pokemon> = ArrayList(), var onClick: (Pokemon) -> Unit) :
    RecyclerView.Adapter<PokemonsListAdapter.PostViewHolder>() {

    inner class PostViewHolder(view: View) : RecyclerView.ViewHolder(view)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_post, parent, false)

        return PostViewHolder(view)
    }

    override fun getItemCount() = pokemons.size

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {

        val current = pokemons[position]

        holder.itemView.postsTitle.text = current.name
        holder.itemView.postsContent.text = current.url
        holder.itemView.setOnClickListener {
            onClick(current)
        }
    }

}