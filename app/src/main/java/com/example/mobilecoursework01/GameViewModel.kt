package com.example.mobilecoursework01

import android.text.Editable
import android.widget.ImageView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {
    //var currentComputer: MutableList<ImageView> = mutableListOf()
    val currentComputer = MutableLiveData<List<ImageView>>()
    val currentHuman = MutableLiveData<List<ImageView>>()
    var humanRoundScore: String = ""
    var computerRoundScore: String = ""
    var targetScore: String = ""
    var humanWinsTotal: String = ""
    var computerWinsTotal: String = ""
    var btnThrow: String = ""

    var throwCounter = 0
    var roundSumHuman = 0
    var roundSumComputer = 0
    var totalSumHuman = 0
    var totalSumComputer = 0

    var totalWinsHuman = 0
    var totalWinsComputer = 0

    var target = 101

}