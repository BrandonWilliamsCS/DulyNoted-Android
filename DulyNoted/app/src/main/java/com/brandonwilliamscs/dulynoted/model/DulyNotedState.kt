package com.brandonwilliamscs.dulynoted.model

import com.brandonwilliamscs.apputil.MultiplexedEvent
import com.brandonwilliamscs.dulynoted.model.music.PitchClass
import com.brandonwilliamscs.dulynoted.view.events.InternalReaction
import com.brandonwilliamscs.dulynoted.view.events.UserIntent

/**
 * Contains the entire state for the Duly Noted application. Includes both "current" screen state
 *  and any background preferences, records, etc.
 * This shouldn't house business logic, but just provide a container for the state and some convenience methods.
 * Created by Brandon on 9/17/2017.
 * TODO: separate into "current view", preferences, statistics, etc.
 * @property currentPromptPitchClass the pitch class to be shown to the user in the prompt
 * @property currentGuess the pitch class selected by the user, if applicable
 */
class DulyNotedState(
        val currentPromptPitchClass: PitchClass,
        val currentGuess: Guess?
) {
    /**
     * Changes the current guess to the value provided.
     * @param answerPitchClass the value of the new guess
     */
    fun updateGuess(answerPitchClass: PitchClass): DulyNotedState
        = DulyNotedState(currentPromptPitchClass, Guess(answerPitchClass, answerPitchClass == currentPromptPitchClass))

    /**
     * Advances the current slideshow one slide.
     */
    fun nextSlide(nextPitchClass: PitchClass) = DulyNotedState(nextPitchClass, null)
}
