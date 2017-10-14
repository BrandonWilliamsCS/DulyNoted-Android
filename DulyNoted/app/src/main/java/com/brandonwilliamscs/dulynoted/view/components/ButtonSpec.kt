package com.brandonwilliamscs.dulynoted.view.components

import android.text.Layout
import com.facebook.litho.Column
import com.facebook.litho.ComponentContext
import com.facebook.litho.ComponentLayout
import com.facebook.litho.annotations.LayoutSpec
import com.facebook.litho.annotations.OnCreateLayout
import com.facebook.litho.annotations.Prop
import com.facebook.litho.widget.Card
import com.facebook.litho.widget.Text
import com.facebook.yoga.YogaAlign
import com.facebook.yoga.YogaEdge


/**
 * Very basic Material-esque button.
 * Created by Brandon on 9/15/2017.
 */
@LayoutSpec(isPureRender = true)
public class ButtonSpec {
    companion object {
        @JvmStatic
        @OnCreateLayout
        fun onCreateLayout(c: ComponentContext, @Prop text: String): ComponentLayout {
            // TODO: work out generic component styling with Litho
            val textComponent = Text.create(c)
                    .text(text)
                    .textSizeDip(18f)
                    .textAlignment(Layout.Alignment.ALIGN_CENTER)
            return Column.create(c)
                    .alignItems(YogaAlign.CENTER)
                    .child(Card.create(c)
                            .content(textComponent)
                            .cornerRadiusDip(2f)
                            .withLayout()
                            .paddingDip(YogaEdge.ALL, 8))
                    .build()
        }
    }
}
