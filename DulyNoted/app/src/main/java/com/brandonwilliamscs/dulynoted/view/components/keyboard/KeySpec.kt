package com.brandonwilliamscs.dulynoted.view.components.keyboard

import com.brandonwilliamscs.dulynoted.R
import com.brandonwilliamscs.dulynoted.model.music.PitchClass
import com.brandonwilliamscs.dulynoted.util.conditionally
import com.brandonwilliamscs.dulynoted.view.events.KeyPressEvent
import com.facebook.litho.*
import com.facebook.litho.annotations.LayoutSpec
import com.facebook.litho.annotations.OnCreateLayout
import com.facebook.litho.annotations.OnEvent
import com.facebook.litho.annotations.Prop
import com.facebook.yoga.YogaAlign

/**
 * Created by Brandon on 9/24/2017.
 */
@LayoutSpec(events = arrayOf(KeyPressEvent::class), isPureRender = true)
class KeySpec {
    companion object {
        @JvmStatic
        @OnCreateLayout
        fun onCreateLayout(
                c: ComponentContext,
                @Prop pitchClass: PitchClass?,
                @Prop(optional = true) highlightColor: Int?
        ): ComponentLayout
                = Column.create(c)
                .child(
                        if (pitchClass == null) FillerKey.create(c)
                        else if (pitchClass.sharpenBaseNote) BlackKey.create(c).highlightColor(highlightColor)
                        else WhiteKey.create(c).highlightColor(highlightColor)
                )
                .widthRes(R.dimen.keyWidth)
                .heightPercent(100f)
                .alignItems(YogaAlign.CENTER)
                .clickHandler(Key.onClick(c))
            .build()

        @JvmStatic
        @OnEvent(ClickEvent::class)
        fun onClick(
                c: ComponentContext,
                @Prop pitchClass: PitchClass?,
                @Prop keyPressHandler: EventHandler<KeyPressEvent>
        ) {
            Key.dispatchKeyPressEvent(
                    keyPressHandler,
                    pitchClass
            )
        }
    }
}
