package com.kdias.thebestmovies.features.detalhesMovies.viewModel

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.google.firebase.database.*
import com.kdias.thebestmovies.features.listaFilmes.fragment.model.Movie

//https://medium.com/android-dev-br/android-ui-bottom-sheet-4709cad826d2
//
//
//https://blog.mindorks.com/using-snaphelper-in-recyclerview-fc616b6833e8
//
//
//https://www.youtube.com/watch?v=iAt9VYLdpUo


class DetalhesMoviesViewModel : ViewModel(){

     val ref: DatabaseReference  by lazy {
        FirebaseDatabase.getInstance().getReference()
    }

    var movie : ObservableField<Movie> = ObservableField()

    fun loadData( movieId: String) {

        val select = ref.child("Movies").orderByChild("id").equalTo(movieId)

        select.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(p0: DataSnapshot) {
                if (p0.exists()) {

                    for (m in p0.children) {
                        val movieResult = m.getValue(Movie::class.java)
                        Log.e("movieresult", movieResult?.title)
                        movie.set(movieResult)

                    }
                }
            }

        })
    }
}