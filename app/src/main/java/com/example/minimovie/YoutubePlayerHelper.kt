package com.example.minimovie

import com.google.android.youtube.player.*

const val YOUTUBE_API_KEY = "AIzaSyDDaVF7AD3kH3rsHbNFQT1wyINxSW1_LlQ"

fun YouTubeThumbnailView.setYoutubeThumbnail(videoId: String) {
    this.initialize(
        YOUTUBE_API_KEY,
        object : YouTubeThumbnailView.OnInitializedListener {
            override fun onInitializationSuccess(
                youTubeThumbnailView: YouTubeThumbnailView,
                youTubeThumbnailLoader: YouTubeThumbnailLoader
            ) {
                youTubeThumbnailLoader.setVideo(videoId)
                youTubeThumbnailLoader.setOnThumbnailLoadedListener(object :
                    YouTubeThumbnailLoader.OnThumbnailLoadedListener {
                    override fun onThumbnailLoaded(
                        youTubeThumbnailView: YouTubeThumbnailView,
                        p1: String?
                    ) {
                        youTubeThumbnailLoader.release()
                    }

                    override fun onThumbnailError(
                        youTubeThumbnailView: YouTubeThumbnailView,
                        errorReason: YouTubeThumbnailLoader.ErrorReason
                    ) {

                    }
                })
            }

            override fun onInitializationFailure(
                youTubeThumbnailView: YouTubeThumbnailView,
                youTubeInitializationResult: YouTubeInitializationResult
            ) {

            }
        })
}

fun YouTubePlayerView.setYoutubeVideo(videoId: String) {
    this.initialize(
        YOUTUBE_API_KEY,
        object : YouTubePlayer.OnInitializedListener {
            override fun onInitializationSuccess(
                provider: YouTubePlayer.Provider?,
                youtubePlayer: YouTubePlayer?,
                wasRestored: Boolean
            ) {
                if (!wasRestored) {
                    youtubePlayer?.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT)
                    youtubePlayer?.loadVideo(videoId)

                    youtubePlayer?.setFullscreen(true)
                    youtubePlayer?.play()
                }
            }

            override fun onInitializationFailure(
                provider: YouTubePlayer.Provider?,
                youTubeInitializationResult: YouTubeInitializationResult
            ) {
            }
        }
    )
}
