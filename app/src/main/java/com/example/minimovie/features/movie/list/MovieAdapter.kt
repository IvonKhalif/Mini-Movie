package com.example.minimovie.features.movie.list

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.minimovie.databinding.ItemMovieBinding
import com.example.minimovie.repository.movie.MoviesDTO
import com.jakewharton.rxbinding2.view.clicks
import java.util.concurrent.TimeUnit

class MovieAdapter(
    private val listener: (model: MoviesDTO.ResultMovie) -> Unit
) : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {
    private var movieList = ArrayList<MoviesDTO.ResultMovie>()

    fun addMovieList(genres: List<MoviesDTO.ResultMovie>?) {
        genres?.let { movieList.addAll(it) }
        notifyDataSetChanged()
    }

    fun clearList() {
        movieList.clear()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val viewDataBinding = ItemMovieBinding.inflate(inflater, parent, false)
        return ViewHolder(
            viewDataBinding,
            listener
        )
    }

    override fun getItemCount() = movieList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(movieList[position])
    }

    class ViewHolder(
        private val binding: ItemMovieBinding,
        private val listener: (model: MoviesDTO.ResultMovie) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("CheckResult")
        fun bind(resultMovie: MoviesDTO.ResultMovie) {
            binding.movieModel = resultMovie
            binding.executePendingBindings()

            binding.root.clicks()
                .throttleFirst(500, TimeUnit.MILLISECONDS)
                .subscribe {
                    listener(resultMovie)
                }
        }

    }
}