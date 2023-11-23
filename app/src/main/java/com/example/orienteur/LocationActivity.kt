package com.example.orienteur

import android.Manifest
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.net.wifi.WifiManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class LocationActivity : AppCompatActivity() {
    companion object {
        private const val MY_PERMISSIONS_REQUEST_FINE_LOCATION = 1
    }

    private lateinit var wifiManager: WifiManager
    private var scanResultText: String? = null

    private val wifiScanReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            val success = intent.getBooleanExtra(WifiManager.EXTRA_RESULTS_UPDATED, false)
            if (success) {
                scanSuccess()
            } else {
                // Gérer l'échec du scan
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location)

        val endLocal = intent.getStringExtra("END_LOCAL")
        wifiManager = applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager

        // Vérifiez si les permissions sont déjà accordées
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED) {
            // Si les permissions ne sont pas accordées, demandez-les
            ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                MY_PERMISSIONS_REQUEST_FINE_LOCATION)
        } else {
            startWifiScan()
        }

        val localButton: Button = findViewById(R.id.manualLocationButton)
        localButton.setOnClickListener {
            val intent = Intent(this, LocalSelectionActivity::class.java)
            intent.putExtra("END_LOCAL", endLocal)
            startActivity(intent)
        }

        val resultButton: Button = findViewById(R.id.wifiLocationButton)
        resultButton.setOnClickListener {
            if (scanResultText != null) {
                val intent = Intent(this, ResultActivity::class.java)
                intent.putExtra("START_LOCAL", scanResultText)
                intent.putExtra("END_LOCAL", endLocal)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Scan des réseaux en cours.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun startWifiScan() {
        // Enregistrez le BroadcastReceiver ici
        val intentFilter = IntentFilter()
        intentFilter.addAction(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION)
        registerReceiver(wifiScanReceiver, intentFilter)

        // Déclenchez un scan WiFi
        wifiManager.startScan()
    }

    private fun scanSuccess() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            val results = wifiManager.scanResults
            val network1 = results.find { it.SSID == "RASPBERRY_PI_NETWORK_1" }
            val network2 = results.find { it.SSID == "RASPBERRY_PI_NETWORK_2" }

            scanResultText = if (network1 != null && network2 != null) {
                if (network1.level > network2.level) "A-2090 - L'Agora" else "A-2078 - L'Apostrophe"
            } else {
                // Faux local pour l'instant, le simulateur ne peut pas scanner les réseaux WiFi
                "A-2090 - L'Agora"
            }
        }
    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            MY_PERMISSIONS_REQUEST_FINE_LOCATION -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    // La permission a été accordée
                    startWifiScan()
                } else {
                    // La permission a été refusée
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(wifiScanReceiver)
    }
}