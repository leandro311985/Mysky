package com.example.mysky.view

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mysky.Adapter.MovieAdapter
import com.example.mysky.R
import com.example.mysky.viewModel.MovieViewModel
import com.example.mysky.viewModel.Repository
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    var progressBar: ProgressBar? = null
    private lateinit var viewModel: MovieViewModel
    private var adapter: MovieAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        progressBar = findViewById(R.id.loadmore_progress)
        onResponse()
    }

    private fun onResponse() {
        viewModel = ViewModelProvider(this,
            MovieViewModel.MainViewModelFactory(
                Repository()
            )
        ).get(
            MovieViewModel::class.java
        )
        viewModel.moviesLiveData.observe(this, Observer {
            if (it.isNotEmpty()){
                progressBar?.visibility = View.GONE
            }
            adapter = MovieAdapter(this@MainActivity, it)
            gvMovies.adapter = adapter

        })
        viewModel.moviesLiveData
    }

}
