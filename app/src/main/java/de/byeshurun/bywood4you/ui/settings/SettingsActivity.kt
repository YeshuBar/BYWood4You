package de.byeshurun.bywood4you.ui.settings

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import de.byeshurun.bywood4you.R
import de.byeshurun.bywood4you.logic.database.DbManager
import de.byeshurun.bywood4you.logic.files.CsvFileHandler

class SettingsActivity : AppCompatActivity(), OnClickListener {

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 100
    }

    private lateinit var readCurrentLocation: Button
    private lateinit var exportDbDataToCsvFiles: Button
    private lateinit var startPhoneActivity: Button
    private lateinit var locationData: TextView

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_activity_layout)

        readCurrentLocation = findViewById(R.id.readCurrentLocation)
        exportDbDataToCsvFiles = findViewById(R.id.exportDbDataToCsvFiles)
        startPhoneActivity = findViewById(R.id.startPhoneActivity)
        locationData = findViewById(R.id.locationData)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        readCurrentLocation.setOnClickListener(this)
        exportDbDataToCsvFiles.setOnClickListener(this)
        startPhoneActivity.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v?.id) {
            R.id.readCurrentLocation -> getCurrentLocation()
            R.id.exportDbDataToCsvFiles -> exportDbDataToCsvFiles()
            R.id.startPhoneActivity -> startPhoneActivity()
        }
    }

    private fun exportDbDataToCsvFiles() {
        val usersFromDb = DbManager.getInstance(this).getAllUsers()
        CsvFileHandler.getInstance().exportUsersToCsvFile(this, usersFromDb)
    }

    private fun startPhoneActivity() {
        this.startActivity(
            Intent(
                Intent.ACTION_DIAL,
                Uri.parse(this.getString(R.string.uri_phone_number))
            )
        )
    }

    private fun getCurrentLocation() {
        if (
            ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
            &&
            ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            requestLocationPermission()
        } else {
            fusedLocationClient.lastLocation
                .addOnSuccessListener { location: Location? ->
                    location?.let {
                        val latitude = it.latitude
                        val longitude = it.longitude
                        val locationText =
                            getString(R.string.location_coordinates, latitude, longitude)
                        locationData.text = locationText
                    } ?: run {
                        locationData.text = getString(R.string.location_not_found)
                    }
                }
                .addOnFailureListener { e ->
                    val errorMessage = getString(R.string.location_failure, e.message)
                    locationData.text = errorMessage

                }
        }
    }

    private fun requestLocationPermission() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
            LOCATION_PERMISSION_REQUEST_CODE
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getCurrentLocation()
            } else {
                locationData.text = getString(R.string.location_permission_denied)
            }
        }
    }

}