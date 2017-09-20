package com.brandonwilliamscs.dulynoted.view.components

import com.brandonwilliamscs.dulynoted.util.AndroidUtil
import com.brandonwilliamscs.dulynoted.util.children
import com.brandonwilliamscs.dulynoted.view.components.keyboard.WhiteKey
import com.brandonwilliamscs.dulynoted.view.events.UserIntent
import com.brandonwilliamscs.dulynoted.view.events.UserIntentEvent
import com.facebook.litho.*
import com.facebook.litho.annotations.LayoutSpec
import com.facebook.litho.annotations.OnCreateLayout
import com.facebook.litho.annotations.OnEvent
import com.facebook.litho.annotations.Prop
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
                // Functional craziness: this is a very traditional funtional idiom, but maybe not Kotlin.
                // Basically, we're naming a value and then creating a block where it can be used by that name.
                =AndroidUtil.computeDeviceWidthDp(c.resources.displayMetrics).let { deviceWidth ->
                    (deviceWidth / 50).toInt().let { whiteKeyCount ->
                        Row.create(c)
                                .children(
                                        IntRange(1, whiteKeyCount).asSequence()
                                                .map {
                                                    WhiteKey.create(c)
                                                    .withLayout()
                                                    //!! dimen here. And/or dynamic by screen width.
                                                    .widthDip(50)
                                                    .maxHeightPercent(100f)
                                                    .clickHandler(ResponseArea.onNextClick(c))
                                                })
                                .justifyContent(YogaJustify.CENTER)
                                .alignItems(YogaAlign.FLEX_END)
                                .build()
                    }
                }

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
