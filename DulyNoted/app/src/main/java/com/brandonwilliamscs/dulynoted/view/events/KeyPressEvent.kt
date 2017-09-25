package com.brandonwilliamscs.dulynoted.view.events

import com.brandonwilliamscs.dulynoted.model.music.PitchClass
import com.facebook.litho.annotations.Event

/**
 * Created by Brandon on 9/25/2017.
 */
@Event
class KeyPressEvent {
    lateinit var pitchClass: PitchClass
}
