package com.brandonwilliamscs.dulynoted.Model.Music

/**
 * Represents 12 divisions within a scale.
 *
 * Created by Brandon on 9/10/2017.
 */
data class PitchClass private constructor(
        val baseNoteLetter: NoteLetter,
        val sharpenBaseNote: Boolean,
        private val cacheIndex: Int
) {

    companion object {
        private val pitchClasses = listOf(
            PitchClass(NoteLetter.C, false, 0),
            PitchClass(NoteLetter.C, true, 1),
            PitchClass(NoteLetter.D, false, 2),
            PitchClass(NoteLetter.D, true, 3),
            PitchClass(NoteLetter.E, false, 4),
            PitchClass(NoteLetter.F, false, 5),
            PitchClass(NoteLetter.F, true, 6),
            PitchClass(NoteLetter.G, false, 7),
            PitchClass(NoteLetter.G, true, 8),
            PitchClass(NoteLetter.A, false, 9),
            PitchClass(NoteLetter.A, true, 10),
            PitchClass(NoteLetter.B, false, 11)
        )

        public val C: PitchClass = pitchClasses[0];

        public fun getPitchClass(baseNoteLetter: Char, sharpenBaseNote: Boolean = false): PitchClass {
            // TODO: prevent case where letter can't be sharpened
            return pitchClasses.first { it.matches(baseNoteLetter, sharpenBaseNote) }
        }

        public fun getPitchClass(baseNoteLetter: NoteLetter, sharpenBaseNote: Boolean = false): PitchClass
            = getPitchClass(baseNoteLetter.letter, sharpenBaseNote)
    }

    public fun matches(baseNoteLetter: Char, sharpenBaseNote: Boolean): Boolean
        = baseNoteLetter == this.baseNoteLetter.letter && sharpenBaseNote == this.sharpenBaseNote

    public fun increasePitch(amount: Int, includeAllSemiTones: Boolean = false): PitchClass {
        if (includeAllSemiTones) {
            return pitchClasses[(cacheIndex + amount) % 12]
        } else {
            val letter = baseNoteLetter.increaseLetter(amount)
            return getPitchClass(letter)
        }
    }
}

//!! move to where it belongs
fun PitchClass.Companion.getRandomPitchClass(includeAllSemiTones: Boolean = false): PitchClass {
    val adjustmentRange = if (includeAllSemiTones) 12 else 7
    val adjustment = (Math.random() * adjustmentRange).toInt()
    return PitchClass.C.increasePitch(adjustment, includeAllSemiTones)
}