package com.brandonwilliamscs.dulynoted.model

import com.brandonwilliamscs.apputil.MultiplexedEvent
import com.brandonwilliamscs.apputil.StateMachine
import com.brandonwilliamscs.dulynoted.model.music.PitchClass
import com.brandonwilliamscs.dulynoted.view.events.InternalReaction
import com.brandonwilliamscs.dulynoted.view.events.UserIntent
import io.reactivex.Observable
import io.reactivex.Observer
import java.util.concurrent.TimeUnit

/**
 * The root of all app logic for Duly Noted.
 * Created by Brandon on 9/28/2017.
 */
class DulyNotedModel() {
    //!! Don't allow repeat prompt?
    //!! comments here, paying attention to javadoc

    /**
     * Encapsulates the application's initial state.
     */
    val initialState = DulyNotedState(PitchClass.getRandomPitchClass(), null)

    // rely on the state machine for the complexity of the event/model interactions.
    // oddly enough, there are no side-effects within the state machine, because it's all RX.
    val stateMachine = StateMachine(initialState, this::processEvent)

    val viewEventObserver: Observer<UserIntent> get() = stateMachine.viewEventObserver
    val stateChangeObservable: Observable<DulyNotedState> = stateMachine.stateChangeObservable

    fun guessAnswer(state: DulyNotedState, answerPitchClass: PitchClass): DulyNotedState {
        return if (state.currentGuess?.isCorrect == true)
            // Ignore key presses if we already have the correct answer highlighted
            state
        else {
            if (answerPitchClass == state.currentPromptPitchClass) {
                // Also, signal that we'd like to move on to the next prompt.
                //!! extract constant
                delayInternalEvent(InternalReaction.FinishReportingAnswer, 500)
            }
            state.updateGuess(answerPitchClass)
        }
    }

    fun finishReportingAnswer(state: DulyNotedState): DulyNotedState
            // TODO: allow true
            = state.nextSlide(PitchClass.getRandomPitchClass(false))

    private fun delayInternalEvent(event: InternalReaction, delayMS: Long) {
        stateMachine.modelEventObserver.onNext(Observable.timer(delayMS, TimeUnit.MILLISECONDS).map { event })
    }

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
