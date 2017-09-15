package com.brandonwilliamscs.dulynoted.model.music

/**
 * Encodes some behavior and type safety around
 * Created by Brandon on 9/10/2017.
 */
enum class NoteLetter(val letter: Char) {
    C('C'),
    D('D'),
    E('E'),
    F('F'),
    G('G'),
    A('A'),
    B('B');

    public fun fromChar(char: Char) = when (char) {
        'A' -> A
        'B' -> B
        'C' -> C
        'D' -> D
        'E' -> E
        'F' -> F
        'G' -> G
        // TODO: better exceptions
        else -> throw Exception("Cannot form Note Letter from char: " + char)
    }

    public fun increaseLetter(amount: Int): NoteLetter {
        val currentLetterOffset = letter.minus('A')
        val letterOffset = (currentLetterOffset + amount) % 7
        val letter = 'A'.plus(letterOffset)
        return fromChar(letter)
    }
}
