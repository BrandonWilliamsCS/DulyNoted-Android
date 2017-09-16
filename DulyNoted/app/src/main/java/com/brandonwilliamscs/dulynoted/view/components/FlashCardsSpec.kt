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
 * Created by Brandon on 9/15/2017.
 */
@LayoutSpec
class FlashCardsSpec {
    companion object {
        @JvmStatic
        @OnCreateLayout
        fun onCreateLayout(c: ComponentContext, @Prop promptPitchClass: PitchClass): ComponentLayout
                = Column.create(c)
                .child(PromptArea.create(c).pitchClass(promptPitchClass)
                        .withLayout().maxHeightPercent(50f).flexGrow(1f))
                .child(ResponseArea.create(c)
                        .withLayout().maxHeightPercent(50f).flexGrow(1f))
                .build()
    }
}
