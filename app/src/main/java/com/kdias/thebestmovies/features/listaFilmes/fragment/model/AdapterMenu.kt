package com.kdias.thebestmovies.features.listaFilmes.fragment.model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.kdias.thebestmovies.R
import kotlinx.android.synthetic.main.item_list_menu.view.*

class AdapterMenu(private val list: List<MenuModel>, val clickList: (menu: MenuModel) -> Unit) :
    RecyclerView.Adapter<AdapterMenu.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list_menu, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val menu = list[position]
        holder.bindView(menu)
        holder.itemView.setOnClickListener { clickList(menu) }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(menu: MenuModel) {
            val name = itemView.name
            name.text = menu.name
            val description = itemView.description
            description.text = menu.description
            val price = itemView.price
            price.text = menu.price.toString()
            val ivMenu = itemView.thumbnail
            Glide.with(ivMenu)
                .load(menu.thumbnail)
                .apply(
                    RequestOptions().placeholder(R.drawable.loading_animation)
                        .error(R.drawable.loading_img)
                )
                .into(ivMenu)
        }

    }
}