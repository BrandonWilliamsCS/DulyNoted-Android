package com.brandonwilliamscs.dulynoted.model

import com.brandonwilliamscs.apputil.MultiplexedEvent
import com.brandonwilliamscs.apputil.StateMachine
import com.brandonwilliamscs.dulynoted.model.music.PitchClass
import com.brandonwilliamscs.dulynoted.view.events.InternalReaction
import com.brandonwilliamscs.dulynoted.view.events.UserIntent
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.Scheduler
import java.util.concurrent.TimeUnit

/**
 * The root of all app logic for Duly Noted.
 * Houses the state machine and reports "internal" events to it, when appropriate.
 * Created by Brandon on 9/28/2017.
 */
class DulyNotedModel(scheduler: Scheduler) {

    /**
     * Encapsulates the application's initial state.
     */
    val initialState = DulyNotedState(PitchClass.getRandomPitchClass(), null)

    /**
     * Rely on the state machine for the complexity of the event/model interactions.
     * Oddly enough, there are no side-effects within the state machine, because it's all RX.
     */
    val stateMachine = StateMachine(initialState, this::processEvent, scheduler)

    // The following are just pass-through properties.
    val viewEventObserver: Observer<UserIntent> get() = stateMachine.viewEventObserver
    val stateChangeObservable: Observable<DulyNotedState> get() = stateMachine.stateChangeObservable

    /**
     * Respond to the user guessing an answer
     * @param state the state at the time of the event
     * @param answerPitchClass the answer guessed
     */
    fun guessAnswer(state: DulyNotedState, answerPitchClass: PitchClass): DulyNotedState {
        return if (state.currentGuess?.isCorrect == true)
            // Ignore key presses if we already have the correct answer highlighted
            state
        else {
            if (answerPitchClass == state.currentPromptPitchClass) {
                // Also, signal that we'd like to move on to the next prompt.
                // TODO: take 500 from a preference
                delayInternalEvent(InternalReaction.FinishReportingAnswer, 500)
            }
            state.updateGuess(answerPitchClass)
        }
    }

    /**
     * Clear the current slide and create a new one.
     * @param state the state at the time of the event
     */
    fun finishReportingAnswer(state: DulyNotedState): DulyNotedState
            // TODO: allow true
            = state.nextSlide(PitchClass.getRandomPitchClass(false, state.currentPromptPitchClass))

    /**
     * Selects a transformation function based on the incoming event and applies it to the provided state.
     * @param startState the state to transform
     * @param event the event used to indicate which transformation to apply
     */
    fun processEvent(startState: DulyNotedState, event: MultiplexedEvent<UserIntent, InternalReaction>): DulyNotedState {
        return if (event.isViewEvent) processUserIntent(startState, event.fromView)
        else processInternalReaction(startState, event.fromModel)
    }

    /**
     * Selects a transformation function based on a user intent.
     * @param startState the state to transform
     * @param userIntent the user intent that indicates which transformation to apply
     */
    private fun processUserIntent(startState: DulyNotedState, userIntent: UserIntent): DulyNotedState {
        return when (userIntent) {
            is UserIntent.GuessAnswer -> guessAnswer(startState, userIntent.pitchClass)
        }
    }

    /**
     * Selects a transformation function based on an internal reaction.
     * @param startState the state to transform
     * @param internalReaction the internal reaction event used to indicate which transformation to apply
     */
    private fun processInternalReaction(startState: DulyNotedState, internalReaction: InternalReaction): DulyNotedState {
        return when (internalReaction) {
            InternalReaction.FinishReportingAnswer -> finishReportingAnswer(startState)
        }
    }

    /**
     * Utility for emitting an event after a delay.
     */
    private fun delayInternalEvent(event: InternalReaction, delayMS: Long) {
        stateMachine.modelEventObserver.onNext(Observable.timer(delayMS, TimeUnit.MILLISECONDS).map { event })
    }
}

// Side-effect! Technically, random number generation isn't pure.

/**
 * Generate a random pitch class, optionally allowing for sharpened semi-tones.
 * @param includeAllSemiTones whether or not to include sharpened classes, or just the base letters.
 */
fun PitchClass.Companion.getRandomPitchClass(
        includeAllSemiTones: Boolean = false,
        excludeValue: PitchClass? = null
): PitchClass {
    val adjustmentRange = if (includeAllSemiTones) 12 else 7
    var newValue: PitchClass
    do {
        val adjustment = (Math.random() * adjustmentRange).toInt()
        newValue = PitchClass.C.increasePitch(adjustment, includeAllSemiTones)
    } while (newValue == excludeValue)
    return newValue
}
