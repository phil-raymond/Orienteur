package com.example.orienteur

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val startLocal = intent.getStringExtra("START_LOCAL")
        val endLocal = intent.getStringExtra("END_LOCAL")


        if (startLocal != null && endLocal != null) {
            findPathAndShowVideos(startLocal, endLocal)
        }
    }

    private fun findPathAndShowVideos(startLocal: String, endLocal: String) {
        val startNode = Graph.findNode(startLocal)
        val endNode = Graph.findNode(endLocal)
        if (startNode == null || endNode == null) {
            return
        }
        val path = Graph.findPath(startNode, endNode)

        val videoUrls = path.map { it.videoSequence }

        val intent = Intent(this, VideoActivity::class.java)
        intent.putStringArrayListExtra("VIDEO_URL_LIST", ArrayList(videoUrls))
        startActivity(intent)

    }
}
