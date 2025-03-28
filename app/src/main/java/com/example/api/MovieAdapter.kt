import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.api.Movie
import com.example.api.R

class MovieAdapter(private val movies: List<Movie>) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val movieImageView: ImageView = itemView.findViewById(R.id.movieImageView)
        val movieNameTextView: TextView = itemView.findViewById(R.id.movieNameTextView)
        val movieRatingTextView: TextView = itemView.findViewById(R.id.movieRatingTextView)
        val movieReleaseDateTextView: TextView = itemView.findViewById(R.id.Relaeadedate)
        val movieAverageVoteTextView: TextView = itemView.findViewById(R.id.avarege_vote)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.lsitofrecycleview, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]

        // Agar image null hai to ek default placeholder use karega
        val imageUrl = if (movie.image.isNullOrEmpty()) null else "https://image.tmdb.org/t/p/w500${movie.image}"

        Glide.with(holder.itemView.context)
            .load(imageUrl)
            .apply(RequestOptions().placeholder(R.drawable.bulb).error(R.drawable.bulb))
            .into(holder.movieImageView)

        holder.movieNameTextView.text = movie.title
        holder.movieRatingTextView.text = "Rating: ${movie.vote_average}"
        holder.movieReleaseDateTextView.text = "Release Date: ${movie.release_date}"
        holder.movieAverageVoteTextView.text = "Average Vote: ${movie.vote_average}"
    }

    override fun getItemCount(): Int = movies.size
}