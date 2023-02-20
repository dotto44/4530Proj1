package com.example.a4530proj1

import android.content.ActivityNotFoundException
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity(), View.OnClickListener {
    // Three names entered by the user
    private var firstName: String? = null
    private var middleName: String? = null
    private var lastName: String? = null

    // UI components
    private lateinit var submitButton: Button;
    private lateinit var firstEditText: EditText;
    private lateinit var middleEditText: EditText;
    private lateinit var lastEditText: EditText;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Get UI Components
        submitButton = window.findViewById(R.id.submitButton);
        submitButton.setOnClickListener(this);

        firstEditText = window.findViewById(R.id.firstNameEditText);
        middleEditText = window.findViewById(R.id.middleNameEditText);
        lastEditText = window.findViewById(R.id.lastNameEditText);
    }

    override fun onClick(view : View) {

        when (view.id) {
            R.id.submitButton -> {
                // Get the names from the input fields
                firstName = firstEditText.text.toString()
                middleName = middleEditText.text.toString()
                lastName = lastEditText.text.toString()

                //Check if the EditText string for first/last is empty
                if (firstName.isNullOrBlank() || lastName.isNullOrBlank()) {
                    // If not, throw an error message
                    Toast.makeText(this@MainActivity, "Enter at least a first and last name!", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    // Logged in message
                    Toast.makeText(this@MainActivity, "Logged in!", Toast.LENGTH_SHORT).show()

                    //Start an activity and pass the name strings to it.
                    val messageIntent = Intent(this, LoggedInActivity::class.java)
                    messageIntent.putExtra("NAME_STRING", firstName + " " + lastName)
                    this.startActivity(messageIntent)
                }
            }
            R.id.cameraButton-> {
                val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                try{
                    //cameraLauncher.launch(cameraIntent)
                }catch(ex: ActivityNotFoundException){
                    //Do something here
                }
            }
        }
    }
}