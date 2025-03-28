package com.example.api

import MovieAdapter
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import javax.inject.Inject
import android.Manifest
import android.content.Intent

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    @Inject
    lateinit var movieRepository: MovieRepository

    private val coroutineScope = CoroutineScope(Dispatchers.Main + Job())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.POST_NOTIFICATIONS), 1)
            }
        }


        recyclerView = findViewById(R.id.recyclerView)
        progressBar = findViewById(R.id.progressBar)

        recyclerView.layoutManager = LinearLayoutManager(this)

        fetchMovies()
    }

    private fun fetchMovies() {
        progressBar.visibility = View.VISIBLE  // Show ProgressBar

        coroutineScope.launch {
            val movies = withContext(Dispatchers.IO) {
                movieRepository.getPopularMovies("7069e43f6fc548d7bea36156d0b08a54")

            }

            progressBar.visibility = View.GONE  // Hide ProgressBar

            if (movies != null && movies.isNotEmpty()) {
                recyclerView.adapter = MovieAdapter(movies)  // Set Adapter
                recyclerView.visibility = View.VISIBLE
            } else {
                recyclerView.visibility = View.GONE
                // Optional: Show an error message using Toast or TextView
            }
        }


    }

    override fun onDestroy() {
        super.onDestroy()
        coroutineScope.cancel()  // Cancel the coroutine scope to avoid memory leaks
    }
}