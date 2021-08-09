package com.jungroup.weatherapp

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.lifecycle.lifecycleScope
import com.google.android.gms.location.*
import com.google.android.gms.tasks.OnCompleteListener
import kotlinx.coroutines.launch
import androidx.core.app.ActivityCompat.requestPermissions
import androidx.core.location.LocationManagerCompat.isLocationEnabled
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.google.android.gms.tasks.OnSuccessListener

class SplashActivity : AppCompatActivity() {

    var mFusedLocationClient: FusedLocationProviderClient? = null
    val REQUEST_LOCATION_PERMISSION = 1
    private var mLastLocation: Location? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)


        //GETS AND SAVES LOCATION BEFORE HEADING TO MAIN ACTIVITY
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        getLocation()

    }

    //DATASTORE FUNCTION TO SAVE LOCATION
    private suspend fun save(key: String, value: String) {
        val dataStoreKey = stringPreferencesKey(key)
        applicationContext?.dataStore?.edit { settings ->
            settings[dataStoreKey] = value
        }
    }


    private fun getLocation() {
        if (ActivityCompat.checkSelfPermission(
                applicationContext,
                Manifest.permission.ACCESS_FINE_LOCATION
            )
            != PackageManager.PERMISSION_GRANTED
        ) {
            requestPermissions(
                this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                REQUEST_LOCATION_PERMISSION
            )
        } else {
            mFusedLocationClient!!.lastLocation.addOnSuccessListener { location ->
                if (location != null) {
                    mLastLocation = location
                    Log.d("Got Location", "${location.latitude},${location.longitude}")
                    var coordinates = "${location.latitude},${location.longitude}"
                    lifecycleScope.launch {
                        //SAVE COORDINATES AS STRING
                        save("coordinates", coordinates)
                    }
                    //STARTS NEW ACTIVITY AFTER WE'VE GOTTEN LOCATION
                    val intent = Intent(application, MainActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(applicationContext, "No Location", Toast.LENGTH_LONG).show()
                    Log.d("Location", "No Location")
                    val intent = Intent(application, MainActivity::class.java)
                    startActivity(intent)
                }
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>, grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            REQUEST_LOCATION_PERMISSION ->
                if (grantResults.size > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED
                ) {
                    getLocation()
                } else {
                    Toast.makeText(
                        applicationContext,
                        "permission denied",
                        Toast.LENGTH_SHORT
                    ).show()
                    finish()
                }
        }
    }
}