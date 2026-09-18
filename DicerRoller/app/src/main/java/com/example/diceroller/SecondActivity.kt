package com.example.diceroller

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

/******************************************************************************
 * This code receives the text passed from the MainActivity through the Intent
 * and displays that text on the screen
 * ****************************************************************************/

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        // Find the TextView in the layout
        val textTwo: TextView = findViewById(R.id.TEXTTwo)

        // Read the text passed by the MainActivity, null when nothing was passed
        val receivedText = intent.getStringExtra(EXTRA_TEXT_TO_TWO)

        // update the TextView with the text received from the MainActivity
        textTwo.text = receivedText
    }

    companion object {
        // Key used to pass the text of "textToTwo" between activities
        const val EXTRA_TEXT_TO_TWO = "extra_text_to_two"
    }
}
