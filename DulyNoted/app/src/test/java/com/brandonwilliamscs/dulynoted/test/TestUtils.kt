package com.brandonwilliamscs.dulynoted.test

import org.junit.Assert.assertEquals
import org.junit.Assert.fail

/**
 * Created by Brandon on 9/19/2017.
 */
inline fun <reified T: Any> assertThrows(unit: () -> Unit) {
    try {
        unit()
        fail("expected exception was not occurred.")
    } catch (e: Exception) {
        assertEquals(T::class, e.javaClass.kotlin)
    }

}
