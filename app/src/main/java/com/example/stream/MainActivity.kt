package com.example.stream

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.stream.databinding.ActivityMainBinding
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.ui.PlayerView
import com.google.gson.GsonBuilder


data class reelclass(val video_url: String,val display_url: String)
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    companion object {
        private const val CAMERA_PERMISSION_CODE = 100
        private const val STORAGE_PERMISSION_CODE = 101
    }
//    var binding: ActivityMainBinding? = null

    var url: String = "1"
    var link = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val player: ExoPlayer = ExoPlayer.Builder(this).build()
        val playerView: PlayerView = findViewById(R.id.playerview)
        playerView.setPlayer(player)
        // Build the media item.

        binding.storage.setOnClickListener {

                link = binding.edittext.text.toString()
                if (!link.isEmpty()) {
                    var ups = link.substringBefore('?')
                    ups += "?__a=1&__d=dis"
                    val urll = ups
                    /////////////////////////////

                    Log.d("finalurl", urll)

                    val request =
                        StringRequest(Request.Method.GET, urll,
                            { response ->
                                val gsonBuilder = GsonBuilder()
                                val gson = gsonBuilder.create()
                                val mainURL = gson.fromJson(response, MainURL::class.java)
                                val reelurl = mainURL.graphql.shortcode_media.getVideo_url()
                                val uri2 = Uri.parse(reelurl)
                                url= uri2.toString()
                                Log.d("reelurl", url)
                                Log.d("outsidereelurl",url)

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
                                binding.edittext.text.clear()

//                                for download video
                                // fileName -> fileName with extension
                                val request = DownloadManager.Request(Uri.parse(url))
                                    .setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI or DownloadManager.Request.NETWORK_MOBILE)
                                    .setTitle("reel")
                                    .setDescription("don't know")
                                    .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                                    .setAllowedOverMetered(true)
                                    .setAllowedOverRoaming(false)
                                    .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,"reel")
                                val downloadManager= getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
                                downloadManager.enqueue(request)
                                //////////download
                            }, object : Response.ErrorListener {
                            override fun onErrorResponse(error: VolleyError) {
                                Log.d("volley error", error.toString())
                            }
                        })
                  val queue = Volley.newRequestQueue(this)
                    queue.add(request)


                        }

                        ////////////////////////////
//            Toast.makeText(this,"$link  is goog",Toast.LENGTH_SHORT).show()
                        url = link
//                        Toast.makeText(this, "$url  is url", Toast.LENGTH_SHORT).show()
//                        val mediaItem: MediaItem = MediaItem.fromUri(url)
//// Set the media item to be played.
//// Set the media item to be played.
//                        player.setMediaItem(mediaItem)
//                        player.addMediaItem(mediaItem)
//// Prepare the player.
//// Prepare the player.
//                        player.prepare()
//// Start the playback.
//// Start the playback.
//                        player.play()
//                        binding.edittext.text.clear()

//            url =link
//        val mediaItem: MediaItem = MediaItem.fromUri(url)
//// Set the media item to be played.
//// Set the media item to be played.
//        player.setMediaItem(mediaItem)
//        player.addMediaItem(mediaItem)
//// Prepare the player.
//// Prepare the player.
//        player.prepare()
//// Start the playback.
//// Start the playback.
//        player.play()

                    ////////////


                }
            }
        }

