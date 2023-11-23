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

        initializeGraph()

        val arrivalSpinner: Spinner = findViewById(R.id.arrivalSpinner)

        // Données pour les Spinners
        val arrivalOptions = arrayOf(
            "A-2090 - L'Agora",
            "A-2078 - L'Apostrophe",
            "A-2185 - Socioculturel",
            "A-2179 - Services Psychosociaux",
            "A-2357 - Bureau de la recherche",
            "A-2154 - Labo informatique",
            "D-2500 - Bibliothèque")

        // Adapteurs pour les Spinners
        val arrivalAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, arrivalOptions)
        arrivalAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        arrivalSpinner.adapter = arrivalAdapter


        // Gestion du clic sur le bouton
        val searchButton: Button = findViewById(R.id.searchButton)
        searchButton.setOnClickListener {
            val selectedDestination = arrivalSpinner.selectedItem.toString()
            val intent = Intent(this, LocationActivity::class.java)
            intent.putExtra("END_LOCAL", selectedDestination)
            startActivity(intent)
        }
    }

    private fun initializeGraph() {
        val node1 = Node("A-2090 - L'Agora")
        val node2 = Node("A-2078 - L'Apostrophe")
        val node3 = Node("A-2185 - Socioculturel")
        val node4 = Node("A-2179 - Services Psychosociaux")
        val node5 = Node("A-2357 - Bureau de la recherche")
        val node6 = Node("A-2154 - Labo informatique")
        val node7 = Node("D-2500 - Bibliothèque")

        Graph.addNode(node1)
        Graph.addNode(node2)
        Graph.addNode(node3)
        Graph.addNode(node4)
        Graph.addNode(node5)
        Graph.addNode(node6)
        Graph.addNode(node7)

        val edge1 = Edge(node1, node2, "agora_apostrophe")
        val edge2 = Edge(node2, node3, "apostrophe_socio")
        val edge3 = Edge(node3, node4, "socio_services")
        val edge4 = Edge(node4, node5, "services_recherche")
        val edge5 = Edge(node5, node6, "recherche_labo")
        val edge6 = Edge(node6, node7, "labo_bibli")

        Graph.addEdge(edge1)
        Graph.addEdge(edge2)
        Graph.addEdge(edge3)
        Graph.addEdge(edge4)
        Graph.addEdge(edge5)
        Graph.addEdge(edge6)
    }
}
