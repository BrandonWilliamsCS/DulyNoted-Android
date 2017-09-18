package com.brandonwilliamscs.dulynoted.model

import com.brandonwilliamscs.dulynoted.model.music.PitchClass

/**
 * Contains the entire state for the Duly Noted application. Includes both "current" screen state
 *  and any background preferences, records, etc.
 * Created by Brandon on 9/17/2017.
 *
 * @property currentPromptPitchClass the pitch class to be shown to the user in the prompt
 */
class DulyNotedState(val currentPromptPitchClass: PitchClass) {
    /**
     * Advances the current slideshow one slide, if appropriate.
     *
     * Note: this *can* be static now, but it eventually shouldn't be that simple.
     */
    fun nextSlideRequested() = DulyNotedState(PitchClass.getRandomPitchClass())

    companion object {
        /**
         * Encapsulates the application's initial state.
         */
        val initialState = DulyNotedState(PitchClass.getRandomPitchClass())
    }
}

// Side-effect! Technically, random number generation isn't pure.

/**
 * Generate a random pitch class, optionally allowing for sharpened semi-tones.
 * @param includeAllSemiTones whether or not to include sharpened classes, or just the base letters.
 */
fun PitchClass.Companion.getRandomPitchClass(includeAllSemiTones: Boolean = false): PitchClass {
    val adjustmentRange = if (includeAllSemiTones) 12 else 7
    val adjustment = (Math.random() * adjustmentRange).toInt()
    return PitchClass.C.increasePitch(adjustment, includeAllSemiTones)
}
