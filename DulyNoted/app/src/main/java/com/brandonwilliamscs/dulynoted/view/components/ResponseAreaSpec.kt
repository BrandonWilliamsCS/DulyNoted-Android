package com.brandonwilliamscs.dulynoted.view.components

import com.brandonwilliamscs.dulynoted.R
import com.brandonwilliamscs.dulynoted.view.events.UserIntent
import com.brandonwilliamscs.dulynoted.view.events.UserIntentEvent
import com.facebook.litho.*
import com.facebook.litho.annotations.LayoutSpec
import com.facebook.litho.annotations.OnCreateLayout
import com.facebook.litho.annotations.OnEvent
import com.facebook.litho.annotations.Prop

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
                = Column.create(c)
                .child(Button.create(c)
                        .text(c.resources.getString(R.string.next))
                        .withLayout()
                        .clickHandler(ResponseArea.onNextClick(c)))
                .build()

        @JvmStatic
        @OnEvent(ClickEvent::class)
        fun onNextClick(
                c: ComponentContext,
                @Prop userIntentHandler: EventHandler<UserIntentEvent>
        ) {
            ResponseArea.dispatchUserIntentEvent(
                    userIntentHandler,
                    UserIntent.NextSlide
            );
        }
    }
}
