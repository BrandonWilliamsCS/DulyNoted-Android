package com.brandonwilliamscs.dulynoted.view.components

import com.brandonwilliamscs.dulynoted.model.music.PitchClass
import com.brandonwilliamscs.dulynoted.view.components.keyboard.Keyboard
import com.brandonwilliamscs.dulynoted.view.events.KeyPressEvent
import com.brandonwilliamscs.dulynoted.view.events.UserIntent
import com.brandonwilliamscs.dulynoted.view.events.UserIntentEvent
import com.facebook.litho.*
import com.facebook.litho.annotations.*
import com.facebook.yoga.YogaAlign
import com.facebook.yoga.YogaJustify

/**
 * Displays the "response" part of a flashcard. This may be any supported format.
 * Created by Brandon on 9/15/2017.
 */
@LayoutSpec(events = arrayOf(UserIntentEvent::class), isPureRender = true)
class ResponseAreaSpec {
    companion object {
        @JvmStatic
        @OnCreateLayout
        fun onCreateLayout(c: ComponentContext): ComponentLayout
                = Row.create(c)
                .child(Keyboard.create(c)
                        .startPitchClass(PitchClass.C)
                        .count(12)
                        .keyPressHandler(ResponseArea.onKeyPress(c))
                        .withLayout()
                        .heightPercent(100f))
                .justifyContent(YogaJustify.CENTER)
                .alignItems(YogaAlign.FLEX_END)
                .build()

        @JvmStatic
        @OnEvent(KeyPressEvent::class)
        fun onKeyPress(
                @Suppress("UNUSED_PARAMETER") c: ComponentContext,
                @Prop userIntentHandler: EventHandler<UserIntentEvent>,
                @FromEvent pitchClass: PitchClass
        ) {
            ResponseArea.dispatchUserIntentEvent(
                    userIntentHandler,
                    UserIntent.GuessAnswer(pitchClass)
            )
        }
    }
}
