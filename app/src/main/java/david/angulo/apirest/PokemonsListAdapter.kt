package david.angulo.apirest

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import david.angulo.apirest.model.Pokemon
import kotlinx.android.synthetic.main.item_pokemon.view.*

class PokemonsListAdapter(var pokemons: ArrayList<Pokemon> = ArrayList(),var context: Context, var onClick: (Pokemon) -> Unit) :
    RecyclerView.Adapter<PokemonsListAdapter.PostViewHolder>() {

    inner class PostViewHolder(view: View) : RecyclerView.ViewHolder(view)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_pokemon, parent, false)

        return PostViewHolder(view)
    }

    override fun getItemCount() = pokemons.size

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {

        val current = pokemons[position]

        holder.itemView.tvTitle.text = current.name
        val urlParts = current.url.split("/")
        current.number = Integer.parseInt(urlParts[urlParts.size - 2])

        Glide.with(context)
            .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/" + current.number +".png")
            .into(holder.itemView.ivList)

        holder.itemView.tvSubtitle.visibility = View.GONE

        holder.itemView.setOnClickListener {
            onClick(current)
        }
    }

}