package com.example.orienteur

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.VideoView
import java.util.*

class VideoActivity : AppCompatActivity() {
    private lateinit var videoView: VideoView
    private var videoQueue: Queue<String> = LinkedList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video)

        videoView = findViewById(R.id.videoView)

        val videoUrls = intent.getStringArrayListExtra("VIDEO_URL_LIST")
        if (videoUrls != null) {
            videoQueue.addAll(videoUrls)
        }

        playNextVideo()
    }

    private fun playNextVideo() {
        if (videoQueue.isNotEmpty()) {
            val videoName = videoQueue.remove()
            val videoResId = resources.getIdentifier(videoName, "raw", packageName)
            val videoUri = Uri.parse("android.resource://$packageName/$videoResId")

            videoView.setVideoURI(videoUri)
            videoView.start()

            videoView.setOnCompletionListener {
                playNextVideo()
            }
        }
    }

}
