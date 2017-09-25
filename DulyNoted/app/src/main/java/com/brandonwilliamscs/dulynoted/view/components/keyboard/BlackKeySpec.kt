package com.brandonwilliamscs.dulynoted.view.components.keyboard

import android.graphics.Color
import com.facebook.litho.Column
import com.facebook.litho.ComponentContext
import com.facebook.litho.ComponentLayout
import com.facebook.litho.annotations.LayoutSpec
import com.facebook.litho.annotations.OnCreateLayout
import com.facebook.litho.widget.SolidColor

/**
 * Created by Brandon on 9/20/2017.
 */
@LayoutSpec(isPureRender = true)
class BlackKeySpec {
    companion object {
        @JvmStatic
        @OnCreateLayout
        fun onCreateLayout(c: ComponentContext): ComponentLayout
                = Column.create(c)
                //!! style better
                .child(SolidColor.create(c)
                        .color(Color.BLACK)
                        .withLayout()
                        .widthPercent(100f)
                        .heightPercent(100f))
                .widthPercent(70f)
                .heightPercent(100f)
                .build()
    }
}
