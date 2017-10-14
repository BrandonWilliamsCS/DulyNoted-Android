package com.brandonwilliamscs.dulynoted.test.model

import com.brandonwilliamscs.dulynoted.model.music.NoteLetter
import com.brandonwilliamscs.dulynoted.test.assertThrows
import org.junit.Assert.assertEquals
import org.junit.Test

class NoteLetterTests {

    class FromCharTests {
        @Test
        @Throws(Exception::class)
        fun properlyMatchesLetters() {
            assertEquals(NoteLetter.A, NoteLetter.fromChar('A'))
            assertEquals(NoteLetter.B, NoteLetter.fromChar('B'))
            assertEquals(NoteLetter.C, NoteLetter.fromChar('C'))
            assertEquals(NoteLetter.D, NoteLetter.fromChar('D'))
            assertEquals(NoteLetter.E, NoteLetter.fromChar('E'))
            assertEquals(NoteLetter.F, NoteLetter.fromChar('F'))
            assertEquals(NoteLetter.G, NoteLetter.fromChar('G'))
        }

        @Test
        @Throws(Exception::class)
        fun rejectsInvalidLetters() {
            assertThrows<IllegalArgumentException> {
                NoteLetter.fromChar('H')
                NoteLetter.fromChar('a')
                NoteLetter.fromChar('7')
                NoteLetter.fromChar(' ')
            }
        }
    }

    class IncreaseLetterTests {
        @Test
        @Throws(Exception::class)
        fun doesNothingWithZero() {
            assertEquals(NoteLetter.A, NoteLetter.A.increaseLetter(0))
            assertEquals(NoteLetter.B, NoteLetter.B.increaseLetter(0))
            assertEquals(NoteLetter.C, NoteLetter.C.increaseLetter(0))
            assertEquals(NoteLetter.D, NoteLetter.D.increaseLetter(0))
            assertEquals(NoteLetter.E, NoteLetter.E.increaseLetter(0))
            assertEquals(NoteLetter.F, NoteLetter.F.increaseLetter(0))
            assertEquals(NoteLetter.G, NoteLetter.G.increaseLetter(0))
        }

        @Test
        @Throws(Exception::class)
        fun movesForwardFromNote() {
            assertEquals(NoteLetter.E, NoteLetter.A.increaseLetter(4))
            assertEquals(NoteLetter.D, NoteLetter.C.increaseLetter(1))
            assertEquals(NoteLetter.G, NoteLetter.F.increaseLetter(1))
        }

        @Test
        @Throws(Exception::class)
        fun movesBackwardsWithNegative() {
            assertEquals(NoteLetter.A, NoteLetter.E.increaseLetter(-4))
            assertEquals(NoteLetter.C, NoteLetter.D.increaseLetter(-1))
            assertEquals(NoteLetter.F, NoteLetter.G.increaseLetter(-1))
        }

        @Test
        @Throws(Exception::class)
        fun loopsAroundAtBoundry() {
            assertEquals(NoteLetter.G, NoteLetter.A.increaseLetter(-1))
            assertEquals(NoteLetter.A, NoteLetter.G.increaseLetter(1))
        }
    }
}
