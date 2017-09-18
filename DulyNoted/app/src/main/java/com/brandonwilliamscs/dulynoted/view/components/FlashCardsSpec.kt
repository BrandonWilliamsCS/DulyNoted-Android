package com.brandonwilliamscs.dulynoted.view.components

import com.brandonwilliamscs.dulynoted.model.DulyNotedState
import com.brandonwilliamscs.dulynoted.view.events.UserIntentEvent
import com.facebook.litho.*
import com.facebook.litho.annotations.*


/**
 * Displays the whole flash cards "screen".
 * TODO: split into a "DulyNoted" component and a separate FlashCards view.
 * Created by Brandon on 9/15/2017.
 */
@LayoutSpec(isPureRender = true)
class FlashCardsSpec {
    companion object {
        @JvmStatic
        @OnCreateInitialState
        fun createInitialState(
                c: ComponentContext,
                model: StateValue<DulyNotedState>,
                @Prop initialModel: DulyNotedState) {
            // Side-effect! Sort of. But only due to how the framework wishes to set initial state.
            // In this case, it's a glorified "out" parameter, which is a glorified return value.
            model.set(initialModel)
        }

        @JvmStatic
        @OnCreateLayout
        fun onCreateLayout(c: ComponentContext, @State model: DulyNotedState): ComponentLayout
                = Column.create(c)
                .child(PromptArea.create(c)
                        .pitchClass(model.currentPromptPitchClass)
                        .withLayout().maxHeightPercent(50f).flexGrow(1f))
                .child(ResponseArea.create(c)
                        .userIntentHandler(FlashCards.onUserIntent(c))
                        .withLayout().maxHeightPercent(50f).flexGrow(1f))
                .build()

        @JvmStatic
        @OnEvent(UserIntentEvent::class)
        fun onUserIntent(c: ComponentContext, @State model: DulyNotedState) {
            // TODO: when the model gets more complex, communicate through RX streams instead.
            // At this instant, there's only one user intent, so don't bother looking at it.
            val nextModel = model.nextSlideRequested()
            FlashCards.updateModelAsync(c, nextModel)
        }
        @JvmStatic

        @OnUpdateState
        fun updateModel(
                model: StateValue<DulyNotedState>,
                @Param newModel: DulyNotedState
        ) {
            // Side-effect!
            // This is possibly the only "real" side-effect in the entire app.
            // The others should be contained within a small scope, a library, or won't affect the code at large.
            // For example, each event dispatch or RX stream emission is technically a side-effect. However, they
            //  should never change any state within the project's source code; it's all hidden in the libraries.
            model.set(newModel)
        }
    }
}
