package com.example.mysky.ui

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import com.example.mysky.R
import com.example.mysky.data.Movie
import com.example.mysky.utils.Status
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {

    private val mainViewModel: MovieViewModel by viewModel()

    var progressBar: ProgressBar? = null
    private var adapter: MovieAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        progressBar = findViewById(R.id.loadmore_progress)
        onResponse()
    }

    private fun onResponse() {
        mainViewModel.movies.observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    progressBar?.visibility = View.GONE
                    it.data?.let { movies ->
                        renderList(movies)
                    }

                }
                Status.LOADING -> {
                    progressBar?.visibility = View.VISIBLE

                }
                Status.ERROR -> {
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    progressBar?.visibility = View.GONE

                }
            }
        })

    }

    private fun renderList(movie: List<Movie>) {
        adapter = MovieAdapter(this@MainActivity, movie)
        gvMovies.adapter = adapter
    }

}
