package com.example.orienteur

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

        // Configurer le Spinner avec les options de locaux
        val localOptions = arrayOf("Local 1", "Local 2", "Local 3")
        val localAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, localOptions)
        localAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        localSpinner.adapter = localAdapter

        // Gestion du clic sur le bouton
        val selectLocalButton: Button = findViewById(R.id.selectLocalButton)
        selectLocalButton.setOnClickListener {
        // Logique à exécuter après la sélection du local
        }
    }
}
