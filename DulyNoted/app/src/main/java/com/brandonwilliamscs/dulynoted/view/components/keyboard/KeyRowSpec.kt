package com.brandonwilliamscs.dulynoted.view.components.keyboard

import com.brandonwilliamscs.dulynoted.model.music.PitchClass
import com.brandonwilliamscs.dulynoted.util.children
import com.brandonwilliamscs.dulynoted.util.conditionally
import com.brandonwilliamscs.dulynoted.view.events.KeyPressEvent
import com.facebook.litho.*
import com.facebook.litho.annotations.LayoutSpec
import com.facebook.litho.annotations.OnCreateLayout
import com.facebook.litho.annotations.Prop
import com.facebook.yoga.YogaJustify

/**
 * Created by Brandon on 9/20/2017.
 */
@LayoutSpec(isPureRender = true)
class KeyRowSpec {
    companion object {
        @JvmStatic
        @OnCreateLayout
        fun onCreateLayout(
                c: ComponentContext,
                @Prop pitchClasses: Sequence<PitchClass?>,
                @Prop keyHighlights: Map<PitchClass, Int>,
                @Prop keyPressHandler: EventHandler<KeyPressEvent>
        ): ComponentLayout
                = Row.create(c)
                .children(pitchClasses.map {
                    Key.create(c)
                            .pitchClass(it)
                            .conditionally(keyHighlights.containsKey(it)) {
                                highlightColor(keyHighlights[it])
                            }
                            .keyPressHandler(keyPressHandler)
                })
                .justifyContent(YogaJustify.CENTER)
                .build()
    }
}
