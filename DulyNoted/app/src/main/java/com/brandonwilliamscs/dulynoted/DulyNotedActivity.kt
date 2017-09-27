package com.brandonwilliamscs.dulynoted

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.brandonwilliamscs.apputil.StateMachine
import com.brandonwilliamscs.dulynoted.model.DulyNotedState
import com.brandonwilliamscs.dulynoted.view.components.FlashCards
import com.facebook.litho.ComponentContext
import com.facebook.litho.LithoView
import io.reactivex.android.schedulers.AndroidSchedulers

/**
 * The one and only Activity in Duly Noted.
 * Stuff that's typically handled by switching Activities or Fragments will instead be handled with Litho/state.
 */
class DulyNotedActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val context = ComponentContext(this)
        val initialState = DulyNotedState.initialState
        // rely on the state machine for the complexity of the event/model interactions.
        // oddly enough, there are no side-effects within the state machine, because it's all RX.
        val stateMachine = StateMachine(DulyNotedState.initialState, DulyNotedState.Companion::transformState)

        val lithoView = LithoView.create(
                this /* context */,
                FlashCards.create(context)
                        .initialModel(initialState)
                        .modelStream(stateMachine.stateChangeObservable.observeOn(AndroidSchedulers.mainThread()))
                        .intentStream(stateMachine.viewEventObserver)
                        .build())
        // Side-effect! But it's initialization, so don't count it.
        setContentView(lithoView)
    }
}
