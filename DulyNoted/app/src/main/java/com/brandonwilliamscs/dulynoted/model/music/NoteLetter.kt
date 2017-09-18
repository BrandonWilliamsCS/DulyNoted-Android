package com.brandonwilliamscs.dulynoted.model.music

/**
 * Encodes some behavior and type safety around the characters used as note letters.
 * Created by Brandon on 9/10/2017.
 *
 * @property letter the character form of the note letter
 * @constructor Wraps a character in a smarter enum.
 */
enum class NoteLetter(val letter: Char) {
    C('C'),
    D('D'),
    E('E'),
    F('F'),
    G('G'),
    A('A'),
    B('B');

    /**
     * Retrieve the NoteLetter for a given letter character.
     * @param char a valid musical note letter (A-G)
     * @return the letter, wrapped in a NoteLetter enum
     * @throws IllegalArgumentException the char param is not a valid musical note letter
     */
    fun fromChar(char: Char) = when (char) {
        'A' -> A
        'B' -> B
        'C' -> C
        'D' -> D
        'E' -> E
        'F' -> F
        'G' -> G
        // TODO: better exceptions
        else -> throw IllegalArgumentException("Cannot form Note Letter from char: " + char)
    }

    /**
     * Rotate the current letter "up" in pitch, cycling back from G to A.
     * @param amount how many units to increase
     * @returns the NoteLetter that's the proper number of steps up from this
     */
    fun increaseLetter(amount: Int): NoteLetter {
        val currentLetterOffset = letter.minus('A')
        val letterOffset = (currentLetterOffset + amount) % 7
        val letter = 'A'.plus(letterOffset)
        return fromChar(letter)
    }
}
