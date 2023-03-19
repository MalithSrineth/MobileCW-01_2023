package com.example.mobilecoursework01

import android.text.Editable
import android.widget.ImageView
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {
    var currentComputer: MutableList<ImageView> = mutableListOf()
    var currentHuman: MutableList<ImageView> = mutableListOf()
    var humanRoundScore: String = ""
    var computerRoundScore: String = ""
    var targetScore: String = ""
    var humanWinsTotal: String = ""
    var computerWinsTotal: String = ""

    var throwCounter = 0
    var roundSumHuman = 0
    var roundSumComputer = 0
    var totalSumHuman = 0
    var totalSumComputer = 0

    var totalWinsHuman = 0
    var totalWinsComputer = 0

    var target = 200

}