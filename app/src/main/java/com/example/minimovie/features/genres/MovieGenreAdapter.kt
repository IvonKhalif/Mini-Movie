package com.example.minimovie.features.genres

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.minimovie.R
import com.example.minimovie.databinding.ItemGenreBinding
import com.example.minimovie.repository.genre.GenresDTO

class MovieGenreAdapter(
    private var genreList: List<GenresDTO.Genre>,
    private val selectedGenre: GenresDTO.Genre,
    private val onSelectGenre: (genreModel: GenresDTO.Genre?) -> Unit
): RecyclerView.Adapter<MovieGenreAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val viewDataBinding = ItemGenreBinding.inflate(inflater, parent, false)
        return ViewHolder(viewDataBinding, onSelectGenre)
    }

    override fun getItemCount() = genreList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(genreList[position], selectedGenre)
    }

    class ViewHolder(
        private val binding: ItemGenreBinding,
        private val onSelectGenre: (genreModel: GenresDTO.Genre?) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(
            genreModel: GenresDTO.Genre,
            selectedGenre: GenresDTO.Genre
        ) {
            binding.genre = genreModel
            binding.executePendingBindings()

            if (genreModel.id == selectedGenre.id) {
                binding.textProvinceFilter.setBackgroundResource(R.drawable.background_outline_red)
            } else {
                binding.textProvinceFilter.setBackgroundResource(R.drawable.background_outline_gray)
            }

            binding.root.setOnClickListener {
                onSelectGenre(genreModel)
            }
        }

    }
}