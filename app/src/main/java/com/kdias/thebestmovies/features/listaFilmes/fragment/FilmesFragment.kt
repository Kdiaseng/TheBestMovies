package com.kdias.thebestmovies.features.listaFilmes.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View

import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kdias.thebestmovies.R
import com.kdias.thebestmovies.features.api.FoodApi
import com.kdias.thebestmovies.features.listaFilmes.fragment.model.AdapterMenu
import com.kdias.thebestmovies.features.listaFilmes.fragment.model.MenuModel
import kotlinx.android.synthetic.main.fragment_list_filmes.*
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class FilmesFragment : Fragment(){
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?{
     val view = inflater.inflate(R.layout.fragment_list_filmes, container, false)!!

    FoodApi.retrofitService.getListMenu().enqueue(object : retrofit2.Callback<List<MenuModel>> {
        override fun onFailure(call: Call<List<MenuModel>>, t: Throwable) {
            progressBar.visibility = View.GONE
        }

        override fun onResponse(call: Call<List<MenuModel>>, response: Response<List<MenuModel>>) {
            progressBar.visibility = View.GONE
            rvList.visibility = View.VISIBLE
            rvList.adapter = AdapterMenu(response.body()!!) {

            }
        }

    })

        return view

    }





}