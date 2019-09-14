package com.kdias.thebestmovies.features.detalhesMovies

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.kdias.thebestmovies.R
import com.kdias.thebestmovies.databinding.ActivityDetalhesMoviesBinding
import com.kdias.thebestmovies.features.detalhesMovies.viewModel.DetalhesMoviesViewModel
import com.kdias.thebestmovies.features.listaFilmes.fragment.model.Movie

class DetalhesMoviesActivity : AppCompatActivity() {


    lateinit var binding: ActivityDetalhesMoviesBinding

    private val viewModel: DetalhesMoviesViewModel by lazy {
        ViewModelProviders.of(this).get(DetalhesMoviesViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhes_movies)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_detalhes_movies)
        binding.viewModel = viewModel

        val movie = intent.extras?.get("movie") as Movie

        movie?.let {

        }

        movie.id?.let { viewModel.loadData(it) }


    }
}
