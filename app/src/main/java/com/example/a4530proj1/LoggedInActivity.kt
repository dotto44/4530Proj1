package com.example.a4530proj1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class LoggedInActivity : AppCompatActivity() {
    private var name: String? = null

    // UI components
    private lateinit var nameText: TextView;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logged_in)

        //Get the intent that created this activity.
        val receivedIntent = intent

        //Get the string data
        name = receivedIntent.getStringExtra("NAME_STRING")

        //Get the UI objects
        nameText = window.findViewById(R.id.loggedText);


        //Set the name component
        nameText.text = name + " is logged in!"
    }
}