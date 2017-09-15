package com.brandonwilliamscs.dulynoted

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.brandonwilliamscs.dulynoted.model.music.PitchClass
import com.brandonwilliamscs.dulynoted.model.music.getRandomPitchClass
import com.brandonwilliamscs.dulynoted.view.components.PromptArea
import com.facebook.litho.ComponentContext
import com.facebook.litho.LithoView
import com.facebook.litho.widget.Text
import com.facebook.soloader.SoLoader

class FlashCardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //!! figure out what to do with this.
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
                PromptArea.create(context)
                        .pitchClass(pitchClass)
                        .build());
        setContentView(lithoView);
    }
}
