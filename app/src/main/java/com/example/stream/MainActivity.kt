package com.example.stream

import android.os.Bundle
import android.text.Editable
import androidx.appcompat.app.AppCompatActivity
import com.example.stream.databinding.ActivityMainBinding
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.source.hls.HlsMediaSource
import com.google.android.exoplayer2.ui.PlayerView


class MainActivity : AppCompatActivity() {
    var binding: ActivityMainBinding? = null
    val url:String ="https://user-images.githubusercontent.com/90382113/170888784-5d9a7623-10c8-4ca2-8585-fdb29b2ed037.mp4"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.root)



        val player: ExoPlayer = ExoPlayer.Builder(this).build()
        val playerView:PlayerView  =findViewById(R.id.playerview)
        playerView.setPlayer(player)


        // Build the media item.

        val mediaItem: MediaItem = MediaItem.fromUri(url)
// Set the media item to be played.
// Set the media item to be played.
        player.setMediaItem(mediaItem)
        player.addMediaItem(mediaItem)
// Prepare the player.
// Prepare the player.
        player.prepare()
// Start the playback.
// Start the playback.
        player.play()

    }
}