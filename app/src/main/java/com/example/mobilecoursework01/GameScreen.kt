package com.example.mobilecoursework01
import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import android.content.res.Configuration
import kotlin.random.Random
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.InputType
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModelProvider
import java.util.*
import java.util.concurrent.locks.Condition

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

    private var totalSumHuman = 0
    private var totalSumComputer = 0

    private var totalWinsHuman = 0
    private var totalWinsComputer = 0

    lateinit var target: Editable

    private lateinit var humanRoundScore: TextView
    private lateinit var computerRoundScore: TextView
    private lateinit var targetScore: TextView

    private lateinit var humanWinsTotal: TextView
    private lateinit var computerWinsTotal: TextView

    private lateinit var btnThrow: Button
    private lateinit var btnScore: Button

    private var selectedDiceMapC: Map<ImageView, RadioButton> = mutableMapOf()
    private var selectedDiceMapH: Map<ImageView, RadioButton> = mutableMapOf()

    private val currentDiceImagesHuman = mutableListOf<ImageView>()
    private val currentDiceImagesComputer = mutableListOf<ImageView>()

    private var currentComputer = mutableListOf<ImageView>()
    private var currentHuman = mutableListOf<ImageView>()

    private lateinit var viewModel: GameViewModel

    @SuppressLint("ResourceType", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_screen)

        viewModel = ViewModelProvider(this).get(GameViewModel::class.java)

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
        targetScore = findViewById<TextView>(R.id.targetScore)

        humanWinsTotal = findViewById<TextView>(R.id.humanWinsTotal)
        computerWinsTotal = findViewById<TextView>(R.id.computerWinsTotal)


        btnThrow = findViewById<Button>(R.id.btnThrow)
        btnScore = findViewById<Button>(R.id.btnScore)

        val radioButtons = arrayOf(
            rbc1, rbc2, rbc3, rbc4, rbc5,
            rbh1, rbh2, rbh3, rbh4, rbh5
        )

        val radioButtonStates = BooleanArray(radioButtons.size)



        if (savedInstanceState == null){
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Set a Target Score")

            val input = EditText(this)
            input.inputType = InputType.TYPE_CLASS_NUMBER
            input.setText("101")
            builder.setView(input)

            builder.setPositiveButton("OK") { dialog, which ->
                target = input.text
                viewModel.target = target.toString().toInt()
                targetScore.text = "$target"
                viewModel.targetScore = "${viewModel.target}"
            }

            builder.setNegativeButton("Cancel") { dialog, which ->
                dialog.cancel()
            }

            val dialog = builder.create()
            dialog.show()
        }

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

        rbc1.isEnabled = false
        rbc2.isEnabled = false
        rbc3.isEnabled = false
        rbc4.isEnabled = false
        rbc5.isEnabled = false

        btnScore.isEnabled = false

        viewModel.currentHuman.value?.let { currentHuman = it.toMutableList() }
        viewModel.currentComputer.value?.let { currentComputer = it.toMutableList() }


        totalWinsHuman = intent.getIntExtra("totalWinsHuman",0)
        totalWinsComputer = intent.getIntExtra("totalWinsComputer",0)
        viewModel.totalWinsHuman = intent.getIntExtra("totalWinsHuman",0)
        viewModel.totalWinsComputer = intent.getIntExtra("totalWinsComputer",0)
        humanWinsTotal.text = intent.getStringExtra("humanWinsTotal")
        computerWinsTotal.text = intent.getStringExtra("computerWinsTotal")



        btnThrow.setOnClickListener {

            if(savedInstanceState != null){
                throwCounter = viewModel.throwCounter
            }
            throwCounter++
            viewModel.throwCounter++

            btnScore.isEnabled = true
            rollDice(currentDiceImagesHuman, selectedDiceMapH, currentHuman)
            viewModel.currentHuman.value = currentHuman.toList()


            if (totalSumHuman >= target.toString().toInt() && totalSumHuman == totalSumComputer){
                throwCounter = 4
                viewModel.throwCounter = 4
                rollDice(currentDiceImagesComputer, selectedDiceMapC, currentComputer)
                viewModel.currentComputer.value = currentComputer.toList()
                btnThrow.isEnabled = false
                btnScore.isPressed = true
                btnScore.performClick()
                btnThrow.text = "Throw"
                viewModel.btnThrow = "Throw"

                Handler(Looper.getMainLooper()).postDelayed({
                    btnThrow.text = "Throw"
                    throwCounter = 0
                    viewModel.throwCounter = 0
                    btnThrow.isEnabled = true
                    btnScore.isPressed = false
                }, 1000)
                btnThrow.text = "Throw (0)"
                viewModel.btnThrow = "Throw (0)"


            } else{
                when (throwCounter) {
                    1 -> {
                        rollDice(currentDiceImagesComputer, selectedDiceMapC, currentComputer)
                        viewModel.currentComputer.value = currentComputer.toList()
                        btnThrow.text = "Throw (2)"
                        viewModel.btnThrow = "Throw (2)"
                    }
                    2 -> {
                        //rollDiceRandom()
                        rollDiceRandomAdvanced(currentComputer)
                        btnThrow.text = "Throw (1)"
                        viewModel.btnThrow = "Throw (1)"
                    }
                    3 -> {
                        //rollDiceRandom()
                        rollDiceRandomAdvanced(currentComputer)
                        btnThrow.isEnabled = false
                        btnScore.isPressed = true
                        btnScore.performClick()
                        btnThrow.text = "Throw"
                        viewModel.btnThrow = "Throw"


                        Handler(Looper.getMainLooper()).postDelayed({
                            btnThrow.text = "Throw"
                            viewModel.btnThrow = "Throw"
                            throwCounter = 0
                            viewModel.throwCounter = 0
                            btnThrow.isEnabled = true
                            btnScore.isPressed = false
                        }, 1000)
                        btnThrow.text = "Throw (0)"
                        viewModel.btnThrow = "Throw (0)"

                    }
                }
            }
        }

        btnScore.setOnClickListener{

            while (throwCounter<3){
                rollDiceRandomAdvanced(currentComputer)
                throwCounter++
            }

            throwCounter = 0
            btnThrow.isEnabled = false

            Handler(Looper.getMainLooper()).postDelayed({
                btnThrow.text = "Throw"
                viewModel.btnThrow = "Throw"
                btnThrow.isEnabled = true
                resetRound()
                val builderAlert = AlertDialog.Builder(this)
                Toast.makeText(applicationContext, "You : $roundSumHuman | Computer : $roundSumComputer", Toast.LENGTH_SHORT).show()

                if (totalSumComputer >= target.toString().toInt() && totalSumComputer > totalSumHuman){
                    totalWinsComputer += 1
                    viewModel.totalWinsComputer += 1
                    computerWinsTotal.text = "${viewModel.totalWinsComputer}"

                    builderAlert.setTitle("You lose")
                    builderAlert.setMessage("Better luck next time!")

                    builderAlert.setIcon(R.drawable.lose)
                    builderAlert.setPositiveButton("OK") { dialog, which ->
                        // do something when OK button is clicked
                        computerRoundScore.text = "0"
                        humanRoundScore.text = "0"

                        totalSumHuman = 0
                        totalSumComputer = 0
                        viewModel.totalSumHuman = 0
                        viewModel.totalSumComputer = 0
                    }
                    builderAlert.show().getButton(DialogInterface.BUTTON_POSITIVE).setTextColor(Color.RED)


                }
                else if (totalSumHuman >= target.toString().toInt() && totalSumHuman > totalSumComputer){
                    totalWinsHuman += 1
                    viewModel.totalWinsHuman += 1
                    humanWinsTotal.text = "${viewModel.totalWinsHuman}"

                    builderAlert.setTitle("You win!")
                    builderAlert.setMessage("Congratulations!")
                    builderAlert.setIcon(R.drawable.won)
                    builderAlert.setPositiveButton("OK") { dialog, which ->
                        // do something when OK button is clicked
                        computerRoundScore.text = "0"
                        humanRoundScore.text = "0"

                        totalSumHuman = 0
                        totalSumComputer = 0
                        viewModel.totalSumHuman = 0
                        viewModel.totalSumComputer = 0
                    }
                    builderAlert.show().getButton(DialogInterface.BUTTON_POSITIVE).setTextColor(Color.GREEN)
                }

                roundSumComputer=0
                roundSumHuman=0
                viewModel.roundSumComputer=0
                viewModel.roundSumHuman=0
                currentComputer.clear()
                currentHuman.clear()
                btnScore.isEnabled = false
            }, 1000)


        }
    }

    override fun onBackPressed() {
        val gameIntent = Intent(this, MainActivity::class.java)
        gameIntent.putExtra("totalWinsComputer", viewModel.totalWinsComputer)
        gameIntent.putExtra("totalWinsHuman", viewModel.totalWinsHuman)
        gameIntent.putExtra("computerWinsTotal", computerWinsTotal.text)
        gameIntent.putExtra("humanWinsTotal", humanWinsTotal.text)
        startActivity(gameIntent)

    }


    @SuppressLint("ResourceType")
    private fun rollDice(diceImages: List<ImageView>, selectedDiceMap: Map<ImageView, RadioButton>, thisRoundDiceSet: MutableList<ImageView>) {
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

    private fun rollDiceRandom() {
        if (isReRoll()){
            val set = generateUniqueRandomNumbers()
            for (number in set) {
                selectedDiceMapC.values.toList()[number].isChecked = true
            }
            rollDice(currentDiceImagesComputer, selectedDiceMapC, currentComputer)
        }
    }

    //In this random computer strategy, dice with values 5 and 6 are retained and others are rerolled.
    //In the 2nd attempt dice with values 4, 5, and 6 are retained and others are rerolled.
    //In the 3rd attempt computer checks the human player's score. If the gap between target and the human player's score
    //is less than 15, dice with value 3 also retained. Else the program try to get more higher values by trial and error.
    //This method can be used to score very high scores because in every roll the dice with highest values are retained to maximize the sum.
    private fun rollDiceRandomAdvanced(diceImages: List<ImageView>) {
        var counter = 0
        var isAvailable: Boolean = false
        when (throwCounter){
            2 -> {
                for (imageView in diceImages) {
                    val tag = imageView.tag
                    if (tag == 5 || tag == 6) {
                        selectedDiceMapC.values.toList()[counter].isChecked = true
                        isAvailable = true
                    }
                    counter++
                }
                rollDice(currentDiceImagesComputer, selectedDiceMapC, currentComputer)
                viewModel.currentComputer.value = currentComputer.toList()
            }

            3 -> {

                for (imageView in diceImages) {
                    val tag = imageView.tag
                    if (tag == 4 || tag == 5 || tag == 6) {
                        selectedDiceMapC.values.toList()[counter].isChecked = true
                        isAvailable = true
                    }
                    counter++
                }

                if (isAvailable && (target.toString().toInt() - totalSumHuman) < 15){
                    counter = 0
                    for (imageView in diceImages) {
                        val tag = imageView.tag
                        if (tag == 3) {
                            selectedDiceMapC.values.toList()[counter].isChecked = true
                        }
                        counter++
                    }
                }
                rollDice(currentDiceImagesComputer, selectedDiceMapC, currentComputer)
                viewModel.currentComputer.value = currentComputer.toList()


            }
        }

    }

    private fun isReRoll(): Boolean {
        //val random = Random()
        return Random.nextBoolean()
    }

    private fun generateUniqueRandomNumbers(): Set<Int> {
        val set = mutableSetOf<Int>()
        val randomSize = Random.nextInt(1, 5)
        while (set.size < randomSize) {
            val randomNumber = Random.nextInt(randomSize+1)
            set.add(randomNumber)
        }
        return set
    }


    private fun sumDice(diceImages: List<ImageView>): Int {
        var sum = 0
        for (imageView in diceImages) {
            val tag = imageView.tag
            if (tag != null) {
                sum += tag.toString().toInt()
            }
        }
        return sum
    }

    private fun resetRound(){
        roundSumComputer = sumDice(currentComputer)
        roundSumHuman = sumDice(currentHuman)

        viewModel.roundSumComputer = sumDice(currentComputer)
        viewModel.roundSumHuman = sumDice(currentHuman)

        totalSumComputer+=roundSumComputer
        totalSumHuman+=roundSumHuman

        viewModel.totalSumComputer+=roundSumComputer
        viewModel.totalSumHuman+=roundSumHuman

        humanRoundScore.text = "${viewModel.totalSumHuman}"
        computerRoundScore.text = "${viewModel.totalSumComputer}"



        val drawableIdCom = resources.getIdentifier("c0", "drawable", packageName)
        val comHolderDrawable = resources.getDrawable(drawableIdCom, null)

        val drawableIdHum = resources.getIdentifier("h0", "drawable", packageName)
        val humHolderDrawable = resources.getDrawable(drawableIdHum, null)

        for ((dice, radioButton) in selectedDiceMapC) {
            dice.setImageDrawable(comHolderDrawable)
            radioButton.isChecked = false
        }

        for ((dice, radioButton) in selectedDiceMapH) {
            dice.setImageDrawable(humHolderDrawable)
            radioButton.isChecked = false
        }


    }
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("humanRoundScore", viewModel.humanRoundScore)
        outState.putString("computerRoundScore", viewModel.computerRoundScore)
        outState.putString("targetScore", viewModel.targetScore)
        outState.putString("btnThrow", viewModel.btnThrow)

        outState.putInt("throwCounter", viewModel.throwCounter)
        outState.putInt("roundSumHuman", viewModel.roundSumHuman)
        outState.putInt("roundSumComputer", viewModel.roundSumComputer)
        outState.putInt("totalSumHuman", viewModel.totalSumHuman)
        outState.putInt("totalSumComputer", viewModel.totalSumComputer)
        outState.putInt("totalWinsHuman", viewModel.totalWinsHuman)
        outState.putInt("totalWinsComputer", viewModel.totalWinsComputer)

        outState.putBoolean("isDataSaved", true)

        viewModel.currentHuman.value = currentHuman.toList()
        viewModel.currentComputer.value = currentComputer.toList()

        outState.putInt("target", viewModel.target)


    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        viewModel.humanRoundScore = savedInstanceState.getString("humanRoundScore", "")
        viewModel.computerRoundScore = savedInstanceState.getString("computerRoundScore", "")
        viewModel.targetScore = savedInstanceState.getString("targetScore", "")
        viewModel.humanWinsTotal = savedInstanceState.getString("humanWinsTotal", "")
        viewModel.computerWinsTotal = savedInstanceState.getString("computerWinsTotal", "")


        throwCounter = savedInstanceState.getInt("throwCounter")
        roundSumHuman = savedInstanceState.getInt("roundSumHuman")
        roundSumComputer = savedInstanceState.getInt("roundSumComputer")
        totalSumHuman = savedInstanceState.getInt("totalSumHuman")
        totalSumComputer = savedInstanceState.getInt("totalSumComputer")
        totalWinsHuman = savedInstanceState.getInt("totalWinsHuman")
        totalWinsComputer = savedInstanceState.getInt("totalWinsComputer")

        viewModel.btnThrow = savedInstanceState.getString("btnThrow", "")


        val savedTarget = savedInstanceState.getInt("target").toString()
        target = savedTarget.toEditable()

        humanRoundScore.text = totalSumHuman.toString()
        computerRoundScore.text = totalSumComputer.toString()
        targetScore.text = target
        humanWinsTotal.text = totalWinsHuman.toString()
        computerWinsTotal.text = totalWinsComputer.toString()
        viewModel.currentHuman.value?.let { currentHuman = it.toMutableList() }
        viewModel.currentComputer.value?.let { currentComputer = it.toMutableList() }

        var counter = 0
        for ((dice, radioButton) in selectedDiceMapH) {
            val newRandomImage = currentHuman[counter]
            val drawable = newRandomImage.drawable
            dice.setImageDrawable(drawable)
            counter++
        }

        counter = 0
        for ((dice, radioButton) in selectedDiceMapC) {
            val newRandomImage = currentComputer[counter]
            val drawable = newRandomImage.drawable
            dice.setImageDrawable(drawable)
            counter++
        }

        btnThrow.text = viewModel.btnThrow

    }

    private fun String.toEditable(): Editable = Editable.Factory.getInstance().newEditable(this)

}

