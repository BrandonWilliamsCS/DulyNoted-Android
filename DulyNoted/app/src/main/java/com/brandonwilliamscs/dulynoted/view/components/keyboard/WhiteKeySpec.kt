package com.brandonwilliamscs.dulynoted.view.components.keyboard

import android.graphics.Color
import com.brandonwilliamscs.dulynoted.R
import com.facebook.litho.Column
import com.facebook.litho.ComponentContext
import com.facebook.litho.ComponentLayout
import com.facebook.litho.annotations.LayoutSpec
import com.facebook.litho.annotations.OnCreateLayout
import com.facebook.litho.annotations.Prop
import com.facebook.litho.widget.SolidColor
import com.facebook.yoga.YogaEdge

/**
 * Created by Brandon on 9/20/2017.
 */
@LayoutSpec(isPureRender = true)
class WhiteKeySpec {
    companion object {
        @JvmStatic
        @OnCreateLayout
        fun onCreateLayout(c: ComponentContext): ComponentLayout
                = Column.create(c)
                .child(BaseKey.create(c)
                        .color(Color.WHITE))
                .build()
    }
}
