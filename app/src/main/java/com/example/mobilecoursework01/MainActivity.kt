package com.example.mobilecoursework01

import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        var button = findViewById<Button>(R.id.btnAbout)
//
//        button.setOnClickListener {
//            // Inflate the pop-up layout file
//            val inflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
//            val popUpView = inflater.inflate(R.layout.aboutpopup, null)
//
//            // Create the pop-up window
//            val popup = PopupWindow(popUpView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true)
//            popup.showAtLocation(button, Gravity.CENTER, 0, 0)
//
//            // Set up any UI elements inside the pop-up window as desired
//
//        }

        val newGameButton: Button = findViewById(R.id.btnNewGme)
        newGameButton.setOnClickListener{
            startActivity(Intent(this, GameScreen::class.java))
        }


        val aboutButton: Button = findViewById(R.id.btnAbout)
        aboutButton.setOnClickListener {
            // Do something in response to the about button click

            val builder = AlertDialog.Builder(this)
            builder.setTitle("Malith Amarawickrama 20210353")
            builder.setMessage("I confirm that I understand what plagiarism is and have read and understood the section on assessment offences in the essential information for students. The work that I have submitted is entirely my own. Any work from other authors is duly referenced and acknowledged.")
            builder.setNeutralButton("Ok"){dialog, id -> dialog.cancel()}
            builder.show()
        }

    }
    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            val constraintLayout = findViewById<ConstraintLayout>(R.id.constraint_layout)
            constraintLayout.background = resources.getDrawable(R.drawable.wallpaper2, null)
        } else {
            val constraintLayout = findViewById<ConstraintLayout>(R.id.constraint_layout)
            constraintLayout.background = resources.getDrawable(R.drawable.wallpaper, null)
        }
    }




}