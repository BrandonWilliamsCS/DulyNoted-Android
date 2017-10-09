package com.brandonwilliamscs.dulynoted

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.brandonwilliamscs.dulynoted.model.DulyNotedModel
import com.brandonwilliamscs.dulynoted.view.components.FlashCards
import com.facebook.litho.ComponentContext
import com.facebook.litho.LithoView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * The one and only Activity in Duly Noted.
 * Stuff that's typically handled by switching Activities or Fragments will instead be handled with Litho/state.
 */
class DulyNotedActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val context = ComponentContext(this)
        val backgroundScheduler = Schedulers.computation()
        val model = DulyNotedModel(backgroundScheduler)

        val lithoView = LithoView.create(
                this /* context */,
                FlashCards.create(context)
                        .initialModel(model.initialState)
                        .modelStream(model.stateChangeObservable.observeOn(AndroidSchedulers.mainThread()))
                        .intentStream(model.viewEventObserver)
                        .build())
        // Side-effect! But it's initialization, so don't count it.
        setContentView(lithoView)
    }
}
