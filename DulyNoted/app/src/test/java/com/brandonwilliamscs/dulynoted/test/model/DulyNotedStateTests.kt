package com.brandonwilliamscs.dulynoted.test.model

import com.brandonwilliamscs.dulynoted.model.DulyNotedState
import com.brandonwilliamscs.dulynoted.model.music.PitchClass
import org.junit.Assert
import org.junit.Test

/**
 * Created by Brandon on 9/19/2017.
 */
class DulyNotedStateTests {

    val initialState = DulyNotedState(PitchClass.C, null)

    @Test
    @Throws(Exception::class)
    fun canUpdateGuess() {
        val pitchClassD = PitchClass.C.increasePitch(1)
        val nextState = initialState.updateGuess(pitchClassD)
        Assert.assertNotNull(nextState.currentGuess)
        Assert.assertEquals(pitchClassD, nextState.currentGuess!!.value)
        Assert.assertFalse(nextState.currentGuess!!.isCorrect)
    }

    @Test
    @Throws(Exception::class)
    fun canChangeSlide() {
        val pitchClassD = PitchClass.C.increasePitch(1)
        val nextState = initialState.nextSlide(pitchClassD)
        Assert.assertEquals(pitchClassD, nextState.currentPromptPitchClass)
    }
}
