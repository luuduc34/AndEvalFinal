// MovieAdapter.kt

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.technifutur.andeval5.R
import com.technifutur.andeval5.model.Movie

class MovieAdapter(private val movieList: MutableList<Movie>) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_movie, parent, false)
        return MovieViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movieList?.get(position)
        movie?.let {
            holder.nameTextView.text = it.original_title
            holder.dateTextView.text = it.release_date
            holder.rateTextView.text = it.vote_average.toString()
            val url = it.poster_path
            setupImage(url, holder.movieImageView)
        }
    }

    override fun getItemCount(): Int {
        return movieList?.size ?: 0
    }

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
        val dateTextView: TextView = itemView.findViewById(R.id.dateTextView)
        val rateTextView: TextView = itemView.findViewById(R.id.rateTextView)
        val movieImageView: ImageView = itemView.findViewById(R.id.movieImage)
    }

    private fun setupImage(url: String?, imageView: ImageView) {
        if (!url.isNullOrEmpty()) {
            Picasso.get()
                .load("https://image.tmdb.org/t/p/w500$url")
                .fit()
                .centerCrop()
                .into(imageView)
        } else {
            Picasso.get()
                .load("https://upload.wikimedia.org/wikipedia/commons/thumb/6/65/No-Image-Placeholder.svg/660px-No-Image-Placeholder.svg.png?20200912122019")
                .fit()
                .centerCrop()
                .into(imageView)
        }
    }
}

