package com.example.mysky.uiMain

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.mysky.data.Movie
import com.example.mysky.R
import com.example.mysky.uiDetails.DetailsActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_view_recicler.view.*

class MovieAdapter : BaseAdapter {
    var movieList = listOf<Movie>()
    var context: Context? = null

    constructor(context: Context, foodsList: List<Movie>) : super() {
        this.context = context
        this.movieList = foodsList
    }

    override fun getCount(): Int {
        return movieList.size
    }

    override fun getItem(position: Int): Any {
        return movieList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }


    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val movie = this.movieList[position]

        val inflator = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val movieView = inflator.inflate(R.layout.item_view_recicler, null)

        Picasso.get().load(movie.coverUrl).resize(600, 0)
        .placeholder(R.mipmap.place_holder).into(movieView.image)

        movieView.txttitle.text = movie.title
        movieView.image.setOnClickListener {

            val intent = Intent(context, DetailsActivity::class.java)
            intent.putExtra("title", movie.title)
            intent.putExtra("movie", movie)
            context?.startActivity(intent)
        }

        return movieView
    }

}

