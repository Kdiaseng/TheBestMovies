package com.kdias.thebestmovies.features.listaFilmes.fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.kdias.thebestmovies.R

import com.kdias.thebestmovies.features.listaFilmes.fragment.model.Movie
import kotlinx.android.synthetic.main.item_filmes.view.*

import kotlinx.android.synthetic.main.item_list_menu.view.description


class AdapterMovie(private val list: List<Movie>, val clickList: (movie: Movie) -> Unit) :
    RecyclerView.Adapter<AdapterMovie.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_filmes, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val menu = list[position]
        holder.bindView(menu)
        holder.itemView.setOnClickListener { clickList(menu) }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(movie: Movie) {

            val title = itemView.title
            title.text = movie.title

            val description = itemView.description
            description.text = movie.description

            val rutime = itemView.rutime
            rutime.text = movie.runtime

            val poster = itemView.poster
            Glide.with(poster)
                .load(movie.poster)
                .apply(
                    RequestOptions().placeholder(R.drawable.loading_animation)
                        .error(R.drawable.loading_img)
                )
                .into(poster)


        }
    }

}