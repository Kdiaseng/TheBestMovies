package com.kdias.thebestmovies.features.listaSeries

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.*
import com.kdias.thebestmovies.R
import com.kdias.thebestmovies.features.listaFilmes.fragment.AdapterMovie
import com.kdias.thebestmovies.features.listaFilmes.fragment.model.Movie
import kotlinx.android.synthetic.main.fragment_list_filmes.*

class SeriesFragment : Fragment(){

    lateinit var ref: DatabaseReference
    lateinit var movies: MutableList<Movie>
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?):  View?{

        val view =  inflater.inflate(R.layout.fragment_list_filmes, container, false)!!

        movies = mutableListOf()


        ref = FirebaseDatabase.getInstance().getReference()


        ref.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                progressBar.visibility = View.GONE
            }

            override fun onDataChange(p0: DataSnapshot) {
                if (p0.exists()) {
                    movies = mutableListOf()
                    for (m in p0.children) {
                        val movie = m.getValue(Movie::class.java)
                        movies.add(movie!!)

                    }
                    rvList.adapter = AdapterMovie(movies) {

                    }
                    progressBar.visibility = View.GONE
                    rvList.visibility = View.VISIBLE
                    rvList.layoutManager = LinearLayoutManager(context)
                }
            }

        })

        return view

    }


}