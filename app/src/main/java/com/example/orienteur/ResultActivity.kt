package com.example.orienteur

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class ResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        // Récupération du texte des résultats
        val resultText = intent.getStringExtra("RESULT_TEXT")

        // Affichage des résultats
        val resultTextView: TextView = findViewById(R.id.resultTextView)
        resultTextView.text = resultText
    }
}
