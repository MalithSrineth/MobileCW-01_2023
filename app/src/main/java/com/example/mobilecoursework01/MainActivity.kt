package com.example.mobilecoursework01

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.PopupWindow
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val newIntent = intent
        val totalWinsComputer = newIntent.getIntExtra("totalWinsComputer",0)
        val totalWinsHuman = newIntent.getIntExtra("totalWinsHuman",0)
        val computerWinsTotal = newIntent.getStringExtra("computerWinsTotal")
        val humanWinsTotal = newIntent.getStringExtra("humanWinsTotal")



        val newGameButton: Button = findViewById(R.id.btnNewGme)
        newGameButton.setOnClickListener{
            val gameIntent = Intent (this, GameScreen::class.java)
            gameIntent.putExtra("totalWinsComputer", totalWinsComputer)
            gameIntent.putExtra("totalWinsHuman", totalWinsHuman)
            gameIntent.putExtra("computerWinsTotal", computerWinsTotal)
            gameIntent.putExtra("humanWinsTotal", humanWinsTotal)
            startActivity(gameIntent)

        }

        val aboutButton: Button = findViewById(R.id.btnAbout)
        aboutButton.setOnClickListener {
            val inflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val popupView = inflater.inflate(R.layout.about_popup, null)
            val popupWindow = PopupWindow(popupView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            popupWindow.width = ViewGroup.LayoutParams.MATCH_PARENT
            popupWindow.height = ViewGroup.LayoutParams.MATCH_PARENT
            popupWindow.showAtLocation(aboutButton, Gravity.CENTER, 0,0)
            popupView.setOnClickListener{
                popupWindow.dismiss()
            }

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