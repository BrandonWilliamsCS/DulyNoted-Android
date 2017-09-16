package com.brandonwilliamscs.dulynoted.view.components

import com.facebook.litho.Column
import com.facebook.litho.ComponentContext
import com.facebook.litho.ComponentLayout
import com.facebook.litho.annotations.LayoutSpec
import com.facebook.litho.annotations.OnCreateLayout
import com.facebook.yoga.YogaEdge
import com.facebook.yoga.YogaJustify

/**
 * Created by Brandon on 9/15/2017.
 */
@LayoutSpec
class ResponseAreaSpec {
    companion object {
        @JvmStatic
        @OnCreateLayout
        fun onCreateLayout(c: ComponentContext): ComponentLayout
                = Column.create(c)
                .child(Button.create(c)
                        //!! resource
                        .text("Next"))
                .build()
    }
}
