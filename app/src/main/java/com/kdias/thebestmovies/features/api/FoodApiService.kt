package com.kdias.thebestmovies.features.api

import com.kdias.thebestmovies.features.listaFilmes.fragment.model.MenuModel
import com.kdias.thebestmovies.features.listaFilmes.fragment.model.Movie
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.*
import kotlin.collections.HashMap

private const val BASE_URL = "https://tradingnotification.firebaseapp.com/"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface FoodApiService {

    @GET("menu.json")
    fun getListMenu(): Call<List<MenuModel>>

   // @GET("movies.json")
  //  fun moviesBy (@Query("orderBy") propriedade: String, @Query("equalTo") valor: String): Observable<String , Movie>>

//    @GET("comments.json")
//    fun getCommentsMenu(@Query("menu_id") id: Int): Call<List<Comment>>
}

object FoodApi {
    val retrofitService: FoodApiService by lazy {
        retrofit.create(FoodApiService::class.java)
    }
}