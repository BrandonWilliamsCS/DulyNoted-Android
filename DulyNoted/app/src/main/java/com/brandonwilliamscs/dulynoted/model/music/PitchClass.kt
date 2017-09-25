package com.brandonwilliamscs.dulynoted.model.music

/**
 * Represents 12 divisions within a scale.
 * @property baseNoteLetter the letter for this pitch class, before any sharpening or flattening
 * @property sharpenBaseNote whether or not this pitch class is a half-step up from the base letter's pitch class
 * @property cacheIndex used internally for efficiency in computing new instances
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

        /**
         * The pitch class associated with the note letter C.
         */
        val C: PitchClass = pitchClasses[0];

        /**
         * Retrieve the pitch class from the given note letter and optional sharpening.
         * @param baseNoteLetter the note letter for this pitch class, in plain character form
         * @param sharpenBaseNote whether or not this pitch class is a sharp version of the note letter
         */
        fun getPitchClass(baseNoteLetter: Char, sharpenBaseNote: Boolean = false): PitchClass {
            // TODO: prevent case where letter can't be sharpened
            return pitchClasses.first { it.matches(baseNoteLetter, sharpenBaseNote) }
        }

        /**
         * Retrieve the pitch class from the given note letter and optional sharpening.
         * @param baseNoteLetter the note letter for this pitch class, in NoteLetter enum form
         * @param sharpenBaseNote whether or not this pitch class is a sharp version of the note letter
         */
        fun getPitchClass(baseNoteLetter: NoteLetter, sharpenBaseNote: Boolean = false): PitchClass
            = getPitchClass(baseNoteLetter.letter, sharpenBaseNote)
    }

    /**
     * Convenience method for verifying that the pitch class matches the given note letter and sharpness.
     * @param baseNoteLetter the note letter to compare to this pitch class's note letter
     * @param sharpenBaseNote the sharpness to compare to this pitch class's sharpness
     * @return whether or not the pitch class matches the given data
     */
    fun matches(baseNoteLetter: Char, sharpenBaseNote: Boolean): Boolean
        = baseNoteLetter == this.baseNoteLetter.letter && sharpenBaseNote == this.sharpenBaseNote

    /**
     * Adjust the pitch upwards, optionally including each sharpened half-step in the process.
     * @param amount the number of (half-)steps to increase
     * @param includeAllSemiTones whether or not to increase in half-steps rather than letter-by-letter.
     * @return the pitch class located the desired number of (half-)steps above this
     */
    fun increasePitch(amount: Int, includeAllSemiTones: Boolean = false): PitchClass {
        if (includeAllSemiTones) {
            val adjustedAmount = (amount % 12) + 12
            return pitchClasses[(cacheIndex + adjustedAmount) % 12]
        } else {
            val letter = baseNoteLetter.increaseLetter(amount)
            return getPitchClass(letter)
        }
    }

    /**
     * Convenience property for recognizing notes that could have a "sharp" form.
     */
    val hasSharp: Boolean get() = baseNoteLetter == increasePitch(1, true).baseNoteLetter
}
