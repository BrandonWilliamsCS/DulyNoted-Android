package com.brandonwilliamscs.dulynoted.view.components

import com.brandonwilliamscs.dulynoted.model.music.PitchClass
import com.brandonwilliamscs.dulynoted.model.music.getRandomPitchClass
import com.brandonwilliamscs.dulynoted.view.events.UserIntent
import com.brandonwilliamscs.dulynoted.view.events.UserIntentEvent
import com.facebook.litho.*
import com.facebook.litho.annotations.*


/**
 * Created by Brandon on 9/15/2017.
 */
@LayoutSpec
class FlashCardsSpec {
    companion object {
        @JvmStatic
        @OnCreateInitialState
        fun createInitialState(
                c: ComponentContext,
                model: StateValue<PitchClass>,
                @Prop initialModel: PitchClass) {
            // Side-effect! But only due to how the framework wishes to set initial state.
            // In this case, it's a glorified "out" parameter, which is a glorified return value.
            // So in this case, it's not much of a side effect.
            model.set(initialModel)
        }

        @JvmStatic
        @OnCreateLayout
        fun onCreateLayout(c: ComponentContext, @State model: PitchClass): ComponentLayout
                = Column.create(c)
                .child(PromptArea.create(c).pitchClass(model)
                        .withLayout().maxHeightPercent(50f).flexGrow(1f))
                .child(ResponseArea.create(c)
                        .userIntentHandler(FlashCards.onUserIntent(c))
                        .withLayout().maxHeightPercent(50f).flexGrow(1f))
                .build()

        //!! listen to user intents, stream it with RX, listen to model updates.

        @JvmStatic
        @OnEvent(UserIntentEvent::class)
        fun onUserIntent(c: ComponentContext) {
            // TODO: when the model gets more complex, communicate through RX streams instead.
            // At this instant, there's only one user intent, so don't bother looking at it.
            val nextModel = PitchClass.getRandomPitchClass()
            FlashCards.updateModelAsync(c, nextModel)
        }

        @JvmStatic
        @OnUpdateState
        fun updateModel(
                model: StateValue<PitchClass>,
                @Param newModel: PitchClass
        ) {
            // Side-effect!
            // This is one of few real-life side effects that should ever occur aside from those
            //  hidden away and only included for convenience/efficiency.
            model.set(newModel)
        }
    }
}
