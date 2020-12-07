package com.example.minimovie.features.genres


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.minimovie.R
import com.example.minimovie.databinding.BottomSheetGenresBinding
import com.example.minimovie.features.movie.list.MovieViewModel
import com.example.minimovie.repository.genre.GenresDTO
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class GenresBottomSheet() : BottomSheetDialogFragment() {

    private lateinit var viewModel: MovieViewModel
    private lateinit var binding: BottomSheetGenresBinding
    private lateinit var genreAdapter: MovieGenreAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(MovieViewModel::class.java)
        binding = DataBindingUtil.inflate(inflater, R.layout.bottom_sheet_genres, container, false)
        binding.movieViewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listener()
        initAdapter()
    }

    private fun initAdapter() {
        genreAdapter = MovieGenreAdapter(viewModel.genreList.value!!) { genre ->
                viewModel.genreModel.value = genre
                if (genre != null) {
                    viewModel.genreId = genre.id.toString()
                }
                dismiss()
            }

        binding.recyclerGenre.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = genreAdapter
            genreAdapter.notifyDataSetChanged()
        }
    }

    private fun listener() {
        binding.buttonClose.setOnClickListener {
            dismiss()
        }

        binding.buttonClear.setOnClickListener {
            viewModel.genreModel.value = GenresDTO.Genre(0, "")
            viewModel.genreId = ""
            dismiss()
        }
    }
}
