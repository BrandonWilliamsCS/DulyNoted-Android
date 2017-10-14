package com.brandonwilliamscs.dulynoted.test.model

import com.brandonwilliamscs.dulynoted.model.DulyNotedModel
import com.brandonwilliamscs.dulynoted.model.music.PitchClass
import com.brandonwilliamscs.dulynoted.view.events.UserIntent
import io.reactivex.schedulers.Schedulers
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test


/**
 * Created by Brandon on 9/28/2017.
 */
class DulyNotedModelTests {
    @Test
    @Throws(Exception::class)
    fun basicPropertiesWork() {
        val model = DulyNotedModel(Schedulers.trampoline())
        val initialState = model.initialState
        val stateMachine = model.stateMachine
        assertEquals(stateMachine.viewEventObserver, model.viewEventObserver)
        assertEquals(stateMachine.stateChangeObservable, model.stateChangeObservable)
    }

    class GuessAnswerTests {
        @Test
        @Throws(Exception::class)
        fun updatesGuessInStandardCase() {
            val model = DulyNotedModel(Schedulers.trampoline())
            val initialState = model.initialState
            val guessedState = model.guessAnswer(initialState, PitchClass.C)
            assertNotNull(guessedState.currentGuess)
            assertEquals(PitchClass.C, guessedState.currentGuess!!.value)
            assertEquals(PitchClass.C == initialState.currentPromptPitchClass, guessedState.currentGuess!!.isCorrect)
        }

        @Test
        @Throws(Exception::class)
        fun doesNotUpdateCorrectGuess() {
            val model = DulyNotedModel(Schedulers.trampoline())
            val initialState = model.initialState
            val correctlyGuessedState = model.guessAnswer(initialState, initialState.currentPromptPitchClass)
            val secondGuess = initialState.currentPromptPitchClass.increasePitch(1)
            val afterSecondGuess = model.guessAnswer(correctlyGuessedState, secondGuess)
            assertEquals(correctlyGuessedState, afterSecondGuess)
        }

        @Test
        @Throws(Exception::class)
        fun schedulesNextOnCorrect() {
            val model = DulyNotedModel(Schedulers.trampoline())
            val initialState = model.initialState

            val subscribedStream = model.stateChangeObservable.test()

            // This time, trigger the guess with an event instead.
            model.viewEventObserver.onNext(UserIntent.GuessAnswer(initialState.currentPromptPitchClass))

            subscribedStream
                    .awaitCount(3)
                    .assertNoErrors()
                    .assertValueAt(2) { it.currentGuess == null }
        }
    }
}
