package com.brandonwilliamscs.dulynoted.model

import com.brandonwilliamscs.apputil.MultiplexedEvent
import com.brandonwilliamscs.dulynoted.model.music.PitchClass
import com.brandonwilliamscs.dulynoted.view.events.InternalReaction
import com.brandonwilliamscs.dulynoted.view.events.UserIntent

/**
 * Contains the entire state for the Duly Noted application. Includes both "current" screen state
 *  and any background preferences, records, etc.
 * Created by Brandon on 9/17/2017.
 * TODO: separate into "current view", preferences, statistics, etc.
 * @property currentPromptPitchClass the pitch class to be shown to the user in the prompt
 * @property currentAnswerPitchClass the pitch class selected by the user, if applicable
 */
class DulyNotedState(
        val currentPromptPitchClass: PitchClass,
        val currentAnswerPitchClass: PitchClass?
) {
    /**
     * Advances the current slideshow one slide, if appropriate.
     *
     * Note: this *can* be static now, but it eventually shouldn't be that simple.
     */
    fun nextSlideRequested() = DulyNotedState(PitchClass.getRandomPitchClass(), null)

    companion object {
        /**
         * Encapsulates the application's initial state.
         */
        val initialState = DulyNotedState(PitchClass.getRandomPitchClass(), null)

        /**
         * Selects a transformation function based on the incoming event and applies it to the provided state.
         * @param startState the state to transform
         * @param event the event used to indicate which transformation to apply
         */
        fun transformState(startState: DulyNotedState, event: MultiplexedEvent<UserIntent, InternalReaction>): DulyNotedState {
            return if (event.isViewEvent) transformByUserIntent(startState, event.fromView)
            else transformByInternalReaction(startState, event.fromModel)
        }

        /**
         * Selects a transformation function based on a user intent.
         * @param startState the state to transform
         * @param userIntent the user intent that indicates which transformation to apply
         */
        private fun transformByUserIntent(startState: DulyNotedState, userIntent: UserIntent): DulyNotedState {
            return when (userIntent) {
                //!! do something with the actual value
                is UserIntent.GuessAnswer -> startState.nextSlideRequested()
            }
        }

        /**
         * Selects a transformation function based on an internal reaction.
         * @param startState the state to transform
         * @param internalReaction the internal reaction event used to indicate which transformation to apply
         */
        private fun transformByInternalReaction(startState: DulyNotedState, internalReaction: InternalReaction): DulyNotedState {
            return startState
        }
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
