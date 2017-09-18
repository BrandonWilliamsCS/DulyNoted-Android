package com.brandonwilliamscs.dulynoted.view.components

import com.brandonwilliamscs.dulynoted.model.music.PitchClass
import com.facebook.litho.Column
import com.facebook.litho.ComponentContext
import com.facebook.litho.ComponentLayout
import com.facebook.litho.annotations.LayoutSpec
import com.facebook.litho.annotations.OnCreateLayout
import com.facebook.litho.annotations.Prop
import com.facebook.yoga.YogaAlign
import com.facebook.yoga.YogaJustify

/**
 * Displays the "prompt" part of a flashcard. This may be any supported format.
 * Created by Brandon on 9/15/2017.
 */
@LayoutSpec(isPureRender = true)
class PromptAreaSpec {
    companion object {
        @JvmStatic
        @OnCreateLayout
        fun onCreateLayout(c: ComponentContext, @Prop pitchClass: PitchClass): ComponentLayout
                = Column.create(c)
                .child(PromptLetter.create(c)
                        .pitchClass(pitchClass))
                .alignItems(YogaAlign.CENTER)
                .justifyContent(YogaJustify.CENTER)
                .build()
    }
}
