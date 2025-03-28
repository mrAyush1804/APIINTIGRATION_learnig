package com.example.api

import android.content.Context
import android.content.Intent
import com.google.gson.Gson
import okhttp3.OkHttpClient
import android.util.Log
import androidx.core.content.ContextCompat.startActivity
import com.chuckerteam.chucker.api.ChuckerInterceptor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.*
import java.io.IOException
import javax.inject.Inject


class MovieRepository @Inject constructor(
    private val client: OkHttpClient,
    private val gson: Gson,
    private val context: Context
) {
    suspend fun getPopularMovies(apiKey: String): List<Movie>? {
        return withContext(Dispatchers.IO) {
            val url = "https://api.themoviedb.org/3/movie/popular?api_key=$apiKey"


            val request = Request.Builder()
                .url(url)
                .build()

            try {
                val response = client.newCall(request).execute()
                response.body?.string()?.let { json ->
                    val movieResponse = gson.fromJson(json, MovieResponse::class.java)
                    Log.d("MovieRepository", "Response: $json")
                    return@withContext movieResponse.results
                }
            } catch (e: Exception) {
                e.printStackTrace()
                return@withContext null
            }
        }
    }
}
