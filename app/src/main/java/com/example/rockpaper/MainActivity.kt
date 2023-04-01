package com.example.rockpaper

import android.annotation.SuppressLint
import android.content.res.Resources
import android.media.Image
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.app.ActivityCompat
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var playerChoice: String
    private lateinit var computerChoice: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val resultImg = findViewById<ImageView>(R.id.imageViewTop)
        val resultTextView = findViewById<TextView>(R.id.result_text_view)
        val rockButton = findViewById<Button>(R.id.rock_button)
        val paperButton = findViewById<Button>(R.id.paper_button)
        val scissorsButton = findViewById<Button>(R.id.scissors_button)
        val lizardButton = findViewById<Button>(R.id.lizard_button)
        val spockButton = findViewById<Button>(R.id.spock_button)
        val startButton = findViewById<Button>(R.id.start_button)

        rockButton.setOnClickListener { playGame("rock") }
        paperButton.setOnClickListener { playGame("paper") }
        scissorsButton.setOnClickListener { playGame("scissors") }
        lizardButton.setOnClickListener { playGame("lizard") }
        spockButton.setOnClickListener { playGame("spock") }

        startButton.setOnClickListener {         // Disable buttons if the user hasn't made a choice yet
            rockButton.isEnabled = true
            paperButton.isEnabled = true
            scissorsButton.isEnabled = true
            lizardButton.isEnabled = true
            spockButton.isEnabled = true

            val playerChoiceImg = findViewById<ImageView>(R.id.imageViewLeft)
            val computerChoiceImg = findViewById<ImageView>(R.id.imageViewRight)
            playerChoiceImg.setImageResource(R.drawable.pic1)
            computerChoiceImg.setImageResource(R.drawable.pic1)
            resultImg.setImageResource(R.drawable.pic2)
            resultTextView.text = ""
        }
    }

    private fun getResult(choiceOne:String, choiceTwo:String, resultImg:ImageView):String {
        val result: String
        when {
            choiceOne == choiceTwo -> {
                result = getString(R.string.tie)
            }
            choiceOne == "rock" && (choiceTwo == "scissors" || choiceTwo == "lizard") -> {
                result = getString(
                    R.string.win
                )
                resultImg.setImageResource(
                    when (choiceTwo) {
                        "scissors" -> R.drawable.rock_scissors
                        else -> R.drawable.rock_lizard
                    }
                )
            }
            choiceOne == "paper" && (choiceTwo == "rock" || choiceTwo == "spock") ->
            {
                result = getString(
                    R.string.win
                )
                resultImg.setImageResource(
                    when (choiceTwo) {
                        "rock" -> R.drawable.paper_rock
                        else -> R.drawable.paper_spock
                    }
                )
            }
            choiceOne == "scissors" && (choiceTwo == "paper" || choiceTwo == "lizard") -> {
                result = getString(
                    R.string.win
                )
                resultImg.setImageResource(
                    when (choiceTwo) {
                        "paper" -> R.drawable.scissors_paper
                        else -> R.drawable.scissors_lizard
                    }
                )
            }
            choiceOne == "lizard" && (choiceTwo == "paper" || choiceTwo == "spock") -> {
                result = getString(
                    R.string.win
                )
                resultImg.setImageResource(
                    when (choiceTwo) {
                        "paper" -> R.drawable.lizard_paper
                        else -> R.drawable.lizard_spock
                    }
                )
            }
            choiceOne == "spock" && (choiceTwo == "rock" || choiceTwo == "scissors") -> {
                result = getString(
                    R.string.win
                )
                resultImg.setImageResource(
                    when (choiceTwo) {
                        "rock" -> R.drawable.spock_rock
                        else -> R.drawable.spock_scissors
                    }
                )
            }
            else ->  {
                result = getString(R.string.loss)
            }
        }
        return result
    }

    @SuppressLint("DiscouragedApi")
    private fun playGame(player: String) {
        playerChoice = player

        computerChoice = when (Random.nextInt(1, 6)) {
            1 -> "rock"
            2 -> "paper"
            3 -> "scissors"
            4 -> "lizard"
            else -> "spock"
        }

        // Показ результатов
        val resultImg = findViewById<ImageView>(R.id.imageViewTop)
        val resultTextView = findViewById<TextView>(R.id.result_text_view)
        resultTextView.text = getResult(playerChoice, computerChoice, resultImg)
        getResult(computerChoice, playerChoice, resultImg)

        val playerChoiceImg = findViewById<ImageView>(R.id.imageViewLeft)
        val computerChoiceImg = findViewById<ImageView>(R.id.imageViewRight)

        // Находим ресурс картинку по имени файла в зависимости от выбора игрока и компьютера
        val playerResId = this.resources.getIdentifier(playerChoice, "drawable", this.packageName)
        playerChoiceImg.setImageDrawable(ActivityCompat.getDrawable(this, playerResId))
        val computerResId = this.resources.getIdentifier(computerChoice, "drawable", this.packageName)
        computerChoiceImg.setImageDrawable(ActivityCompat.getDrawable(this, computerResId))


        // Выключаем кнопки до следующего раунда
        val rockButton = findViewById<Button>(R.id.rock_button)
        val paperButton = findViewById<Button>(R.id.paper_button)
        val scissorsButton = findViewById<Button>(R.id.scissors_button)
        val lizardButton = findViewById<Button>(R.id.lizard_button)
        val spockButton = findViewById<Button>(R.id.spock_button)
        rockButton.isEnabled = false
        paperButton.isEnabled = false
        scissorsButton.isEnabled = false
        lizardButton.isEnabled = false
        spockButton.isEnabled = false
    }
}
