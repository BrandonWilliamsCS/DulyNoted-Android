package com.brandonwilliamscs.dulynoted.test.model

import com.brandonwilliamscs.dulynoted.model.music.NoteLetter
import com.brandonwilliamscs.dulynoted.model.music.PitchClass
import org.junit.Test

import org.junit.Assert.*

class PitchClassTests {

    @Test
    @Throws(Exception::class)
    fun hasValidBaseC() {
        assertEquals(NoteLetter.C, PitchClass.C.baseNoteLetter)
        assertEquals(false, PitchClass.C.sharpenBaseNote)
    }

    class MatchesTests {
        @Test
        @Throws(Exception::class)
        fun properlyMatchesBaseC() {
            assertTrue(PitchClass.C.matches('C', false))
        }
    }

    class GetPitchClassTests {
        @Test
        @Throws(Exception::class)
        fun properlyMatchesLettersWithCharacter() {
            var desiredLetter = 'A'
            var desiredSharpness = false
            var computedValue = PitchClass.getPitchClass(desiredLetter, desiredSharpness)
            assertEquals(desiredLetter, computedValue.baseNoteLetter.letter)
            assertEquals(desiredSharpness, computedValue.sharpenBaseNote)

            desiredLetter = 'C'
            desiredSharpness = true
            computedValue = PitchClass.getPitchClass(desiredLetter, desiredSharpness)
            assertEquals(desiredLetter, computedValue.baseNoteLetter.letter)
            assertEquals(desiredSharpness, computedValue.sharpenBaseNote)
        }

        @Test
        @Throws(Exception::class)
        fun properlyMatchesLettersWithNoteLetter() {
            var desiredLetter = NoteLetter.A
            var desiredSharpness = false
            var computedValue = PitchClass.getPitchClass(desiredLetter, desiredSharpness)
            assertEquals(desiredLetter, computedValue.baseNoteLetter)
            assertEquals(desiredSharpness, computedValue.sharpenBaseNote)

            desiredLetter = NoteLetter.C
            desiredSharpness = true
            computedValue = PitchClass.getPitchClass(desiredLetter, desiredSharpness)
            assertEquals(desiredLetter, computedValue.baseNoteLetter)
            assertEquals(desiredSharpness, computedValue.sharpenBaseNote)
        }
    }

    class IncreasePitchTests {
        @Test
        @Throws(Exception::class)
        fun doesNothingWithZero() {
            assertEquals(PitchClass.C, PitchClass.C.increasePitch(0))
        }

        @Test
        @Throws(Exception::class)
        fun movesForwardFromNote() {
            assertEquals(PitchClass.getPitchClass('E'), PitchClass.C.increasePitch(2))
            assertEquals(PitchClass.getPitchClass('D'), PitchClass.C.increasePitch(2, true))
        }

        @Test
        @Throws(Exception::class)
        fun movesBackwardsWithNegative() {
            assertEquals(PitchClass.getPitchClass('B'), PitchClass.C.increasePitch(-1))
            assertEquals(PitchClass.getPitchClass('A'), PitchClass.C.increasePitch(-3, true))
        }
    }
}
