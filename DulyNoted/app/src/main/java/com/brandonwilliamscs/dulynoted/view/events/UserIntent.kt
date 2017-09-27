package com.brandonwilliamscs.dulynoted.view.events

import com.brandonwilliamscs.dulynoted.model.music.PitchClass

/**
 * The "meanings" of user actions that come from the UI.
 * Note: This is not the place to store Android "events" like click, rotate, etc. Instead,
 * enumerate an interpretation of what the user wishes to do when performing those interactions.
 * Created by Brandon on 9/17/2017.
 */
sealed class UserIntent {
    // This is what's known as a "variant record" or "discriminated union".
    // There are a pre-defined number of possible "UserIntent" subclasses, so Kotlin is smart about
    //  "when"-switching over the possibilities. It's exhaustive, meaning you don't need a default case.

    /**
     * Represents a user submitting a guess for a flashcard prompt
     * @property pitchClass the value of the user's guess
     */
    class GuessAnswer(val pitchClass: PitchClass) : UserIntent()
}
