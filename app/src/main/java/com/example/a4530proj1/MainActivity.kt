package com.example.a4530proj1

import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Bitmap
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity(), View.OnClickListener {
    // Three names entered by the user
    private var firstName: String? = null
    private var middleName: String? = null
    private var lastName: String? = null

    // The thumbnail image data
    private var thumbnailImage: Bitmap? = null

    // UI components
    private lateinit var submitButton: Button;
    private lateinit var cameraButton: Button;

    private lateinit var firstEditText: EditText;
    private lateinit var middleEditText: EditText;
    private lateinit var lastEditText: EditText;

    private var thumbnail: ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Get UI Components
        submitButton = window.findViewById(R.id.submitButton);
        submitButton.setOnClickListener(this);

        cameraButton = window.findViewById(R.id.cameraButton);
        cameraButton.setOnClickListener(this);

        firstEditText = window.findViewById(R.id.firstNameEditText);
        middleEditText = window.findViewById(R.id.middleNameEditText);
        lastEditText = window.findViewById(R.id.lastNameEditText);

        thumbnail = findViewById<View>(R.id.thumbnail) as ImageView
        if (savedInstanceState != null) {
            thumbnailImage = savedInstanceState.getParcelable("IMAGE_DATA")
            thumbnail!!.setImageBitmap(thumbnailImage)
        }
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
                    messageIntent.putExtra("FIRST_NAME_STRING", firstName)
                    messageIntent.putExtra("LAST_NAME_STRING", lastName)
                    messageIntent.putExtra("MIDDLE_NAME_STRING", middleName)
                    messageIntent.putExtra("IMAGE_DATA", thumbnailImage)
                    this.startActivity(messageIntent)
                }
            }
            R.id.cameraButton-> {
                val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                try{
                    cameraLauncher.launch(cameraIntent)
                }catch(ex: ActivityNotFoundException){}
            }
        }
    }

    private val cameraLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result ->
        if(result.resultCode == RESULT_OK) {
            if (Build.VERSION.SDK_INT >= 33) {
                thumbnailImage = result.data!!.getParcelableExtra("data", Bitmap::class.java)
                thumbnail!!.setImageBitmap(thumbnailImage)
            }
            else{
                thumbnailImage = result.data!!.getParcelableExtra<Bitmap>("data")
                thumbnail!!.setImageBitmap(thumbnailImage)
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        // save the image data
        outState.putParcelable("IMAGE_DATA", thumbnailImage)
    }
}