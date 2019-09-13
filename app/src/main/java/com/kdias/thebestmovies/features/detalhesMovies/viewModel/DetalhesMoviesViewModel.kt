package com.kdias.thebestmovies.features.detalhesMovies.viewModel

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.Disposable

//https://medium.com/android-dev-br/android-ui-bottom-sheet-4709cad826d2
//
//
//https://blog.mindorks.com/using-snaphelper-in-recyclerview-fc616b6833e8
//
//
//https://www.youtube.com/watch?v=iAt9VYLdpUo


class DetalhesMoviesViewModel : ViewModel(){

    lateinit var subscribe: Disposable

    init {
        loadData()
    }

    fun loadData() {

    }


    override fun onCleared() {
        super.onCleared()
        subscribe.dispose()
    }


}