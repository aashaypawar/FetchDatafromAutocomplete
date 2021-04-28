package com.example.storingkeyinmanifest

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.google.android.gms.common.api.Status
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.widget.AutocompleteSupportFragment
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView1 = findViewById<TextView>(R.id.tv1)

        val apikey = "your_string"

        if (!Places.isInitialized()) {
            Places.initialize(applicationContext, apikey)
            Toast.makeText(applicationContext,"Success",Toast.LENGTH_LONG).show()
        }

        //Initialize Autocomplete Fragments
        val autocompleteSupportFragment1 =
                supportFragmentManager.findFragmentById(R.id.autocomplete_fragment1) as AutocompleteSupportFragment?
        autocompleteSupportFragment1!!.setPlaceFields(
                listOf(
                        Place.Field.NAME,
                        Place.Field.ADDRESS,
                        Place.Field.RATING,
                        Place.Field.WEBSITE_URI
                )
        )
        autocompleteSupportFragment1.setOnPlaceSelectedListener(object : PlaceSelectionListener {
            @SuppressLint("SetTextI18n")
            override fun onPlaceSelected(place: Place) {

                val placeName = place.name
                val placeAddress = place.address
                val placeRating = place.rating
                val placeWebsite = place.websiteUri

                textView1.text = placeName +
                        "\n" + placeAddress +
                        "\n" + placeRating + "/5" +
                        "\n" + placeWebsite

            }

            override fun onError(status: Status) {
                Toast.makeText(applicationContext,"Failed",Toast.LENGTH_LONG).show()
            }
        })

        // Append the retrieved info about the place



    }
}
