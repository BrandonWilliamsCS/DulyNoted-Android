package com.brandonwilliamscs.dulynoted.model

import com.brandonwilliamscs.dulynoted.model.music.PitchClass

/**
 * Represents a user's guess, along with that guess's correctness
 * Created by Brandon on 9/27/2017.
 */
class Guess(val value: PitchClass, val isCorrect: Boolean)
