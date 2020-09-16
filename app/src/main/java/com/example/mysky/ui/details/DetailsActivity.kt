package com.example.mysky.ui.details

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.mysky.R
import com.example.mysky.data.Movie
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_details.*
import java.util.*

class DetailsActivity : AppCompatActivity() {

    var textViewMovieYear: TextView? = null
    private var movie: Movie? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        val intent = intent
        movie = intent.getSerializableExtra("movie") as Movie

        val rand = Random()
        val randomNum = rand.nextInt(2)
        Picasso.get().load(movie?.backdropsUrl?.get(randomNum)).into(movie_poster)
        movie_name?.text = movie?.title
        movie_title?.text = movie?.title
        movie_description?.text = movie?.overview
        movie_duration?.text = getString(R.string.duration, movie?.duration)
        textViewMovieYear?.text = movie?.releaseYear
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true

    }
}
