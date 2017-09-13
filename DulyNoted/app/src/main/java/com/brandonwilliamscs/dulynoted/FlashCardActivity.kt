package com.brandonwilliamscs.dulynoted

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.brandonwilliamscs.dulynoted.Model.Music.PitchClass
import com.brandonwilliamscs.dulynoted.Model.Music.getRandomPitchClass
import com.facebook.litho.ComponentContext
import com.facebook.litho.LithoView
import com.facebook.litho.widget.Text
import com.facebook.soloader.SoLoader
import kotlinx.android.synthetic.main.activity_flash_card.*

class FlashCardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flash_card)

        // TODO: consider moving this to an Application class?
        SoLoader.init(this, false)
    }

    override fun onStart() {
        super.onStart()

        //!! this is really just proof-of-concept stuff.
        val context = ComponentContext(this)
        val pitchClass = PitchClass.getRandomPitchClass()
        val lithoView = LithoView.create(
                this /* context */,
                Text.create(context)
                        .text(pitchClass.baseNoteLetter.letter.toString())
                        .textSizeDip(50f)
                        .build());
        setContentView(lithoView);
    }
}
