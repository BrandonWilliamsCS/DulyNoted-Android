package com.brandonwilliamscs.dulynoted.view.components.keyboard

import com.brandonwilliamscs.dulynoted.model.music.PitchClass
import com.brandonwilliamscs.dulynoted.view.events.KeyPressEvent
import com.facebook.litho.*
import com.facebook.litho.annotations.*
import com.facebook.yoga.YogaJustify
import com.facebook.yoga.YogaPositionType

/**
 * Created by Brandon on 9/25/2017.
 */
@LayoutSpec(isPureRender = true)
class KeyboardSpec {
    companion object {
        @JvmStatic
        @OnCreateLayout
        fun onCreateLayout(
                c: ComponentContext,
                @Prop startPitchClass: PitchClass,
                @Prop count: Int,
                @Prop keyPressHandler: EventHandler<KeyPressEvent>
        ): ComponentLayout {
            val (whitePitchClasses, blackPitchClasses) = computeKeyLists(startPitchClass, count)
            return Row.create(c)
                    .child(KeyRow.create(c)
                            .pitchClasses(whitePitchClasses)
                            .keyPressHandler(keyPressHandler))
                    .child(KeyRow.create(c)
                            .pitchClasses(blackPitchClasses)
                            .keyPressHandler(keyPressHandler)
                            .withLayout()
                            .positionType(YogaPositionType.ABSOLUTE)
                            .heightPercent(50f))
                    .widthPercent(100f)
                    .heightPercent(100f)
                    .justifyContent(YogaJustify.CENTER)
                    .build()
        }

        private fun computeKeyLists(startPitchClass: PitchClass, count: Int): Pair<Sequence<PitchClass?>, Sequence<PitchClass?>> {
            val endPitchClass = startPitchClass.increasePitch(count - 1, true)

            // First off, ensure we're processing starting with a white key by moving a black key into a special location.
            val adjustedStart = if (startPitchClass.sharpenBaseNote) startPitchClass.increasePitch(1) else startPitchClass
            val (initialWhitePitchClasses, initialBlackPitchClasses) =
                    if (startPitchClass.sharpenBaseNote) Pair(sequenceOf<PitchClass?>(null), sequenceOf<PitchClass?>(startPitchClass))
                    else Pair(emptySequence<PitchClass?>(), emptySequence<PitchClass?>())

            // now pair off each next white key with the sharpened black or a filler. Stop just before the last white.
            val (middleWhitePitchClasses, middleBlackPitchClasses)
                    = generateSequence(adjustedStart) { it.increasePitch(1) }
                    .takeWhile { it.baseNoteLetter !== endPitchClass.baseNoteLetter }
                    .map { Pair(it, if (it.hasSharp) it.increasePitch(1, true) else null) }
                    .unzip()

            // Now, depending on if we're ending with a white or black key, get ready to tag the end on.
            val finalWhitePitchClasses =
                    if (endPitchClass.sharpenBaseNote) sequenceOf<PitchClass?>(endPitchClass.increasePitch(-1), null)
                    else sequenceOf<PitchClass?>(endPitchClass)
            val finalBlackPitchClasses =
                    if (endPitchClass.sharpenBaseNote) sequenceOf<PitchClass?>(endPitchClass)
                    else emptySequence<PitchClass?>()

            // finally, combine the front, middle, and end of both sets.
            val whitePitchClasses = initialWhitePitchClasses + middleWhitePitchClasses + finalWhitePitchClasses
            val blackPitchClasses = initialBlackPitchClasses + middleBlackPitchClasses + finalBlackPitchClasses
            return Pair(whitePitchClasses, blackPitchClasses)
        }
    }
}
