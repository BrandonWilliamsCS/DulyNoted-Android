package com.brandonwilliamscs.dulynoted.view.components

import com.brandonwilliamscs.dulynoted.R
import com.brandonwilliamscs.dulynoted.model.Guess
import com.brandonwilliamscs.dulynoted.model.music.PitchClass
import com.brandonwilliamscs.dulynoted.util.conditionally
import com.brandonwilliamscs.dulynoted.view.components.keyboard.Keyboard
import com.brandonwilliamscs.dulynoted.view.events.KeyPressEvent
import com.brandonwilliamscs.dulynoted.view.events.UserIntent
import com.brandonwilliamscs.dulynoted.view.events.UserIntentEvent
import com.facebook.litho.ComponentContext
import com.facebook.litho.ComponentLayout
import com.facebook.litho.EventHandler
import com.facebook.litho.Row
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
        fun onCreateLayout(c: ComponentContext, @Prop(optional = true) currentGuess: Guess?): ComponentLayout
                = Row.create(c)
                .child(Keyboard.create(c)
                        .startPitchClass(PitchClass.C)
                        .conditionally(currentGuess != null) {
                            val colorRes = if (currentGuess!!.isCorrect) R.color.correctAnswer else R.color.incorrectAnswer
                            keyHighlight(Pair(currentGuess!!.value, colorRes))
                        }
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
