package com.example.orienteur

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner

class LocalSelectionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_local_selection)

        val localSpinner: Spinner = findViewById(R.id.localSpinner)

        val endLocal = intent.getStringExtra("END_LOCAL")

        // Configurer le Spinner avec les options de locaux
        val localOptions = arrayOf(
            "A-2090 - L'Agora",
            "A-2078 - L'Apostrophe",
            "A-2185 - Socioculturel",
            "A-2179 - Services Psychosociaux",
            "A-2357 - Bureau de la recherche",
            "A-2154 - Labo informatique",
            "D-2500 - Bibliothèque")

        val localAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, localOptions)
        localAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        localSpinner.adapter = localAdapter

        // Gestion du clic sur le bouton
        val selectLocalButton: Button = findViewById(R.id.selectLocalButton)
        selectLocalButton.setOnClickListener {
            val selectedStartPoint = localSpinner.selectedItem.toString()
            val resultIntent = Intent(this, ResultActivity::class.java).apply {
                putExtra("START_LOCAL", selectedStartPoint)
                putExtra("END_LOCAL", endLocal)
            }
            startActivity(resultIntent)
            overridePendingTransition(R.anim.slide_in_scale_fade, R.anim.slide_out_scale_fade)
        }
    }
}
