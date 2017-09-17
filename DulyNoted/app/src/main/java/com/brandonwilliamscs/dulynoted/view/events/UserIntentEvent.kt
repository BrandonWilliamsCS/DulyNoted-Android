package com.brandonwilliamscs.dulynoted.view.events

import com.facebook.litho.annotations.Event

/**
 * Created by Brandon on 9/17/2017.
 */
@Event
class UserIntentEvent {
    lateinit var variant: UserIntent
    // Maybe, in the future, include context info
}
