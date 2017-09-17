package com.brandonwilliamscs.dulynoted

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.brandonwilliamscs.dulynoted.model.music.PitchClass
import com.brandonwilliamscs.dulynoted.model.music.getRandomPitchClass
import com.brandonwilliamscs.dulynoted.view.components.FlashCards
import com.brandonwilliamscs.dulynoted.view.components.PromptArea
import com.facebook.litho.ComponentContext
import com.facebook.litho.LithoView
import com.facebook.litho.stetho.LithoWebKitInspector
import com.facebook.litho.widget.Text
import com.facebook.soloader.SoLoader
import com.facebook.stetho.Stetho

class FlashCardActivity : AppCompatActivity() {
    //!! comment everything, call out impurity (in most general available case, or here as a placeholder)
    // clean up imports, maximize unit test coverage
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val context = ComponentContext(this)
        val pitchClass = PitchClass.getRandomPitchClass()
        val lithoView = LithoView.create(
                this /* context */,
                FlashCards.create(context)
                        .initialModel(pitchClass)
                        .build())
        setContentView(lithoView)
    }
}
