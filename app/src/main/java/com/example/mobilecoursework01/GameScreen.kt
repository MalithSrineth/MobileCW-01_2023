package com.example.mobilecoursework01

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.TextView
import java.util.*

class GameScreen : AppCompatActivity() {

    private lateinit var c1Holder: ImageView
    private lateinit var c2Holder: ImageView
    private lateinit var c3Holder: ImageView
    private lateinit var c4Holder: ImageView
    private lateinit var c5Holder: ImageView

    private lateinit var h1Holder: ImageView
    private lateinit var h2Holder: ImageView
    private lateinit var h3Holder: ImageView
    private lateinit var h4Holder: ImageView
    private lateinit var h5Holder: ImageView

    private lateinit var rbc1: RadioButton
    private lateinit var rbc2: RadioButton
    private lateinit var rbc3: RadioButton
    private lateinit var rbc4: RadioButton
    private lateinit var rbc5: RadioButton

    private lateinit var rbh1: RadioButton
    private lateinit var rbh2: RadioButton
    private lateinit var rbh3: RadioButton
    private lateinit var rbh4: RadioButton
    private lateinit var rbh5: RadioButton

    private var throwCounter = 0
    private var roundSumHuman = 0
    private var roundSumComputer = 0

    private lateinit var humanRoundScore: TextView
    private lateinit var computerRoundScore: TextView

    private lateinit var btnThrow: Button
    private lateinit var btnScore: Button

    private var selectedDiceMapC: Map<ImageView, RadioButton> = mutableMapOf()
    private var selectedDiceMapH: Map<ImageView, RadioButton> = mutableMapOf()

    private val currentDiceImagesHuman = mutableListOf<ImageView>()
    private val currentDiceImagesComputer = mutableListOf<ImageView>()

    private var currentComputer = mutableListOf<ImageView>()
    private var currentHuman = mutableListOf<ImageView>()

    private val tempScores = mutableListOf<Int>()



    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_screen)

        c1Holder = findViewById<ImageView>(R.id.c1Holder)
        c2Holder = findViewById<ImageView>(R.id.c2Holder)
        c3Holder = findViewById<ImageView>(R.id.c3Holder)
        c4Holder = findViewById<ImageView>(R.id.c4Holder)
        c5Holder = findViewById<ImageView>(R.id.c5Holder)

        h1Holder = findViewById<ImageView>(R.id.h1Holder)
        h2Holder = findViewById<ImageView>(R.id.h2Holder)
        h3Holder = findViewById<ImageView>(R.id.h3Holder)
        h4Holder = findViewById<ImageView>(R.id.h4Holder)
        h5Holder = findViewById<ImageView>(R.id.h5Holder)

        rbc1 = findViewById<RadioButton>(R.id.rbc1)
        rbc2 = findViewById<RadioButton>(R.id.rbc2)
        rbc3 = findViewById<RadioButton>(R.id.rbc3)
        rbc4 = findViewById<RadioButton>(R.id.rbc4)
        rbc5 = findViewById<RadioButton>(R.id.rbc5)

        rbh1 = findViewById<RadioButton>(R.id.rbh1)
        rbh2 = findViewById<RadioButton>(R.id.rbh2)
        rbh3 = findViewById<RadioButton>(R.id.rbh3)
        rbh4 = findViewById<RadioButton>(R.id.rbh4)
        rbh5 = findViewById<RadioButton>(R.id.rbh5)

        val imgDiceC1 = ImageView(this)
        val imgDiceC2 = ImageView(this)
        val imgDiceC3 = ImageView(this)
        val imgDiceC4 = ImageView(this)
        val imgDiceC5 = ImageView(this)
        val imgDiceC6 = ImageView(this)

        val imgDiceH1 = ImageView(this)
        val imgDiceH2 = ImageView(this)
        val imgDiceH3 = ImageView(this)
        val imgDiceH4 = ImageView(this)
        val imgDiceH5 = ImageView(this)
        val imgDiceH6 = ImageView(this)

        imgDiceC1.setImageResource(R.drawable.c1)
        imgDiceC2.setImageResource(R.drawable.c2)
        imgDiceC3.setImageResource(R.drawable.c3)
        imgDiceC4.setImageResource(R.drawable.c4)
        imgDiceC5.setImageResource(R.drawable.c5)
        imgDiceC6.setImageResource(R.drawable.c6)

        imgDiceH1.setImageResource(R.drawable.h1)
        imgDiceH2.setImageResource(R.drawable.h2)
        imgDiceH3.setImageResource(R.drawable.h3)
        imgDiceH4.setImageResource(R.drawable.h4)
        imgDiceH5.setImageResource(R.drawable.h5)
        imgDiceH6.setImageResource(R.drawable.h6)

        imgDiceC1.tag = 1
        imgDiceC2.tag = 2
        imgDiceC3.tag = 3
        imgDiceC4.tag = 4
        imgDiceC5.tag = 5
        imgDiceC6.tag = 6

        imgDiceH1.tag = 1
        imgDiceH2.tag = 2
        imgDiceH3.tag = 3
        imgDiceH4.tag = 4
        imgDiceH5.tag = 5
        imgDiceH6.tag = 6

        currentDiceImagesHuman.addAll(listOf(imgDiceH1, imgDiceH2, imgDiceH3, imgDiceH4, imgDiceH5, imgDiceH6))
        currentDiceImagesComputer.addAll(listOf(imgDiceC1, imgDiceC2, imgDiceC3, imgDiceC4, imgDiceC5, imgDiceC6))

        humanRoundScore = findViewById<TextView>(R.id.humanRoundScore)
        computerRoundScore = findViewById<TextView>(R.id.computerRoundScore)

        btnThrow = findViewById<Button>(R.id.btnThrow)
        btnScore = findViewById<Button>(R.id.btnScore)

        val radioButtons = arrayOf(
            rbc1, rbc2, rbc3, rbc4, rbc5,
            rbh1, rbh2, rbh3, rbh4, rbh5
        )

        val radioButtonStates = BooleanArray(radioButtons.size)

        for (i in radioButtons.indices) {
            val radioButton = radioButtons[i]
            radioButton.isChecked = radioButtonStates[i]

            radioButton.setOnClickListener {
                // Toggle the state of the clicked RadioButton
                radioButtonStates[i] = !radioButtonStates[i]
                radioButton.isChecked = radioButtonStates[i]
            }
        }

        // Create a map of ImageView and RadioButton
        selectedDiceMapC = mutableMapOf(
            c1Holder to rbc1,
            c2Holder to rbc2,
            c3Holder to rbc3,
            c4Holder to rbc4,
            c5Holder to rbc5
        )

        selectedDiceMapH = mutableMapOf(
            h1Holder to rbh1,
            h2Holder to rbh2,
            h3Holder to rbh3,
            h4Holder to rbh4,
            h5Holder to rbh5
        )

        btnThrow.setOnClickListener {
            rollDice(currentDiceImagesComputer, selectedDiceMapC, currentComputer)
            rollDice(currentDiceImagesHuman, selectedDiceMapH, currentHuman)
            throwCounter++

            if (throwCounter == 1) {
                btnThrow.text = "Throw (2)"
            }
            if (throwCounter == 2) {
                btnThrow.text = "Throw (1)"
            }
            if (throwCounter == 3) {
                btnThrow.isEnabled = false
                btnScore.isPressed = true
                btnScore.performClick()
                btnThrow.text = "Throw"

                Handler(Looper.getMainLooper()).postDelayed({
                    btnThrow.text = "Throw"
                    throwCounter = 0
                    btnThrow.isEnabled = true
                    btnScore.isPressed = false
                }, 1000)
                btnThrow.text = "Throw (0)"

            }




        }

        btnScore.setOnClickListener{
            throwCounter = 0
            btnThrow.isEnabled = false
            Handler(Looper.getMainLooper()).postDelayed({
                btnThrow.isEnabled = true
                resetRound()
            }, 1000)

        }
    }

    @SuppressLint("ResourceType")
    private fun rollDice(diceImages: List<ImageView>, selectedDiceMap: Map<ImageView, RadioButton>, thisRoundDiceSet: MutableList<ImageView>) {
        //val currentRoundDice = mutableListOf<ImageView>()
        var counter = 0

        for ((dice, radioButton) in selectedDiceMap) {
            if (radioButton.isChecked) {
                counter++
                // If the RadioButton is checked, do not update the dice image
                continue
            }
            if (thisRoundDiceSet.size == 5){
                thisRoundDiceSet.removeAt(counter)
            }

            val newRandomImage = diceImages.random()
            val drawable = newRandomImage.drawable
            dice.setImageDrawable(drawable)
            thisRoundDiceSet.add(counter, newRandomImage)
            counter++
        }

    }

    private fun getRandomDiceImage(value: Int): Int {
        return when (value) {
            1 -> R.drawable.c1
            2 -> R.drawable.c2
            3 -> R.drawable.c3
            4 -> R.drawable.c4
            5 -> R.drawable.c5
            6 -> R.drawable.c6
            7 -> R.drawable.h1
            8 -> R.drawable.h2
            9 -> R.drawable.h3
            10 -> R.drawable.h4
            11 -> R.drawable.h5
            else -> R.drawable.h6
        }
    }

    private fun sumDice(diceImages: List<ImageView>): Int {
        var sum = 0
        for (imageView in diceImages) {
            val tag = imageView.tag
            if (tag != null) {
                sum += tag.toString().toInt()
            }
            //sum += imageView.tag.toString().toInt()
        }
        return sum
    }

    private fun resetRound(){
        roundSumComputer = sumDice(currentComputer)
        roundSumHuman = sumDice(currentHuman)

        humanRoundScore.text = "$roundSumHuman"
        computerRoundScore.text = "$roundSumComputer"

        val drawableIdCom = resources.getIdentifier("c0", "drawable", packageName)
        val comHolderDrawable = resources.getDrawable(drawableIdCom, null)

        val drawableIdHum = resources.getIdentifier("h0", "drawable", packageName)
        val humHolderDrawable = resources.getDrawable(drawableIdHum, null)

        for ((dice, radioButton) in selectedDiceMapC) {
            dice.setImageDrawable(comHolderDrawable)
        }

        for ((dice, radioButton) in selectedDiceMapH) {
            dice.setImageDrawable(humHolderDrawable)
        }
    }


//    private fun sumDice(selectedDiceMap: Map<ImageView, RadioButton>): Int {
//        var sum = 0
//        for ((dice) in selectedDiceMap) {
//            currentComputer.add(dice)
//        }
//        for (imageView in currentComputer) {
//            sum += imageView.tag.toString().toInt()
//        }
//        return sum
//    }
}

