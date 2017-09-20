package com.brandonwilliamscs.dulynoted.test.model

import com.brandonwilliamscs.dulynoted.model.DulyNotedState
import org.junit.Test

/**
 * Created by Brandon on 9/19/2017.
 */
class DulyNotedStateTests {
    @Test
    @Throws(Exception::class)
    fun canAdvanceNextState() {
        val initialState = DulyNotedState.initialState
        val nextState = initialState.nextSlideRequested()
    }
}
