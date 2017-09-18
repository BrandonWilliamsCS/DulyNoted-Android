package com.brandonwilliamscs.dulynoted.view.components

import com.brandonwilliamscs.dulynoted.R
import com.brandonwilliamscs.dulynoted.model.music.PitchClass
import com.facebook.litho.Column
import com.facebook.litho.ComponentContext
import com.facebook.litho.ComponentLayout
import com.facebook.litho.annotations.LayoutSpec
import com.facebook.litho.annotations.OnCreateLayout
import com.facebook.litho.annotations.Prop
import com.facebook.litho.widget.Text

/**
 * Displays a prompt in note letter format.
 * Created by Brandon on 9/15/2017.
 */
@LayoutSpec(isPureRender = true)
class PromptLetterSpec {
    companion object {
        @JvmStatic
        @OnCreateLayout
        fun onCreateLayout(c: ComponentContext, @Prop pitchClass: PitchClass): ComponentLayout
                = Column.create(c).child(
                Text.create(c, 0, R.style.PromptLetter)
                        .text(pitchClass.baseNoteLetter.letter.toString())
        ).build()
    }
}
