package com.zasa.wordle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var wordToGuess: String
    private var triesCount = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        wordToGuess = FourLetterWordList.getRandomFourLetterWord()

        tvHiddenWord.text = wordToGuess

        btnGuess.setOnClickListener {

            //get user input text
            val userWord = etEnterGuess.text.toString().uppercase()
            //check user guess is correct
            val checkers = checkGuess(userWord)

            tvGuess.text =
                StringBuilder(tvGuess.text).append("Guess #$triesCount\nGuess #$triesCount Check\n")
                    .toString()
            tvAnswer.text = StringBuilder(tvAnswer.text).append("$userWord\n$checkers\n").toString()

            // if answer is correct
            if (checkers == "0000") {
                // disable guess button
                btnGuess.isEnabled = false
            } else if (triesCount == 3) {
                btnGuess.visibility = View.INVISIBLE
                Toast.makeText(this, "You have exceeded the number of tries :0", Toast.LENGTH_SHORT)
                    .show()
                tvHiddenWord.visibility = View.VISIBLE
            } else {
                //increase the tries count
                triesCount++
            }
        }
    }

    private fun checkGuess(guess: String): String {
        var result = ""
        for (i in 0..3) {

            if (guess[i] == wordToGuess[i]) {
                result += "O"
            } else if (guess[i] in wordToGuess) {
                result += "+"
            } else {
                result += "X"
            }
        }
        return result
    }


}