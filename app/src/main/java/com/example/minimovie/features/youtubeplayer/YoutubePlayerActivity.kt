package com.example.minimovie.features.youtubeplayer

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.minimovie.R
import com.example.minimovie.databinding.ActivityYoutubePlayerBinding
import com.example.minimovie.features.movie.detail.MovieDetailActivity
import com.example.minimovie.setYoutubeVideo
import com.google.android.youtube.player.YouTubeBaseActivity

class YoutubePlayerActivity : YouTubeBaseActivity() {

    private lateinit var binding: ActivityYoutubePlayerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val trailerKey = intent.getStringExtra(MovieDetailActivity.EXTRA_TRAILER_KEY) ?: ""
        binding = DataBindingUtil.setContentView(this, R.layout.activity_youtube_player)
        binding.youtubePlayerView.setYoutubeVideo(trailerKey)
    }
}
