package com.example.a4530proj1

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class LoggedInActivity : AppCompatActivity() {
    private var firstName: String? = null
    private var lastName: String? = null
    private var middleName: String? = null

    // UI components
    private lateinit var nameText: TextView;

    // The thumbnail image data
    private var thumbnailImage: Bitmap? = null
    private var thumbnail: ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logged_in)

        //Get the intent that created this activity.
        val receivedIntent = intent

        //Get the string data
        firstName = receivedIntent.getStringExtra("FIRST_NAME_STRING")
        lastName = receivedIntent.getStringExtra("LAST_NAME_STRING")
        middleName = receivedIntent.getStringExtra("MIDDLE_NAME_STRING")
        thumbnailImage = receivedIntent.getParcelableExtra("IMAGE_DATA")

        //Get the UI objects
        nameText = window.findViewById(R.id.loggedText);
        thumbnail = findViewById<View>(R.id.thumbnail) as ImageView


        //Set the name component
        nameText.text = firstName + " " + lastName + " is logged in!"

        // Set the thumbnail
        thumbnail!!.setImageBitmap(thumbnailImage)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        // loads data from the received intent so nothing to save
    }
}