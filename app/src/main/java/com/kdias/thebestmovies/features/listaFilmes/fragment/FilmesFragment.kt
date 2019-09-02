package com.kdias.thebestmovies.features.listaFilmes.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View

import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.firebase.database.*
import com.kdias.thebestmovies.R
import com.kdias.thebestmovies.features.api.FoodApi
import com.kdias.thebestmovies.features.listaFilmes.fragment.model.AdapterMenu
import com.kdias.thebestmovies.features.listaFilmes.fragment.model.MenuModel
import com.kdias.thebestmovies.features.listaFilmes.fragment.model.Movie
import kotlinx.android.synthetic.main.fragment_list_filmes.*
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class FilmesFragment : Fragment(){

    lateinit  var ref: DatabaseReference
    lateinit var movies: MutableList<Movie>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?{
     val view = inflater.inflate(R.layout.fragment_list_filmes, container, false)!!

        movies = mutableListOf()

        ref = FirebaseDatabase.getInstance().getReference("Movies")

        ref.addValueEventListener(object: ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
                progressBar.visibility = View.GONE
            }

            override fun onDataChange(p0: DataSnapshot) {
                if (p0.exists()){
                    Log.e("change","CHANGE")
                    for (m in p0.children){
                        val movie = m.getValue(Movie:: class.java)
                        Log.e("MOVIE",movie!!.title)
                        movies.add(movie!!)

                    }
                    progressBar.visibility = View.GONE
                    rvList.visibility = View.VISIBLE
                    Log.i("adpter",movies[0].title)
                    rvList.adapter = AdapterMovie(movies){

                    }
                }
            }

        })


        return view

    }

    override fun onStart() {
        super.onStart()


    }





}