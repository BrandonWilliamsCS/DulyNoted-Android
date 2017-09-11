package com.brandonwilliamscs.dulynoted

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.brandonwilliamscs.dulynoted.Model.Music.PitchClass
import com.brandonwilliamscs.dulynoted.Model.Music.getRandomPitchClass
import kotlinx.android.synthetic.main.activity_flash_card.*

class FlashCardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flash_card)
    }

    override fun onStart() {
        super.onStart()

        //!! this is really just proof-of-concept stuff.
        val pitchClass = PitchClass.getRandomPitchClass()
        promptTextView.text = pitchClass.baseNoteLetter.letter.toString()
    }
}
