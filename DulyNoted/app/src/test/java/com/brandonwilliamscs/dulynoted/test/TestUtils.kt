package com.brandonwilliamscs.dulynoted.test
import junit.framework.Assert.assertEquals
import junit.framework.Assert.fail

/**
 * Created by Brandon on 9/19/2017.
 */
inline fun <reified T: Any> assertThrows(unit: () -> Unit) {
    try {
        unit()
        fail("expected exception was not occured.")
    } catch (e: Exception) {
        assertEquals(T::class, e.javaClass.kotlin)
    }

}
