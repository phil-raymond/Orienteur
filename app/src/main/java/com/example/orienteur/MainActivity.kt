package com.example.orienteur

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val arrivalSpinner: Spinner = findViewById(R.id.arrivalSpinner)

        // Exemple de données pour les Spinners
        val arrivalOptions = arrayOf("Local 1", "Local 2", "Local 3")

        // Adapteurs pour les Spinners
        val arrivalAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, arrivalOptions)
        arrivalAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        arrivalSpinner.adapter = arrivalAdapter


        // Gestion du clic sur le bouton
        val searchButton: Button = findViewById(R.id.searchButton)
        searchButton.setOnClickListener {
            val intent = Intent(this, LocationActivity::class.java)
            startActivity(intent)
        }

    }
}
