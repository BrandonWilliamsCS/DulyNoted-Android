package com.brandonwilliamscs.dulynoted.view.components

import com.brandonwilliamscs.dulynoted.view.events.UserIntent
import com.brandonwilliamscs.dulynoted.view.events.UserIntentEvent
import com.facebook.litho.*
import com.facebook.litho.annotations.LayoutSpec
import com.facebook.litho.annotations.OnCreateLayout
import com.facebook.litho.annotations.OnEvent
import com.facebook.litho.annotations.Prop

/**
 * Created by Brandon on 9/15/2017.
 */
@LayoutSpec(events = arrayOf(UserIntentEvent::class))
class ResponseAreaSpec {
    companion object {
        @JvmStatic
        @OnCreateLayout
        fun onCreateLayout(c: ComponentContext): ComponentLayout
                = Column.create(c)
                .child(Button.create(c)
                        //!! resource
                        .text("Next")
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
