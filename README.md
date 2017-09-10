# DulyNoted-Android

## Terminology:

- *Keyboard*: A full or partial piano keyboard

- *Note*: A single playable unit that can be represent

- *Note Position*: A position on a staff where a Note may appear

- *Note Value*: The duration of a note, in terms of fraction of a whole.

- *Octave*: A single band of Pitches, divided into the appropraite Pitch Classes

- *Pitch*: A frequency of music, representing one Pitch Class in one Octave

- *Pitch Class*: The "letter" portion of the Pitch - not Octave specific

## Library Considerations

Retrofit (networking library) + GSON or Moshi (JSON parsing) + Glide or Picasso (image loading) + RxJava + Dagger2 (dependency injection) + Mockito and PowerMockito (mocking in unit tests)
That's kinda the golden lineup that a lot of people are using and looking at.

Butterknife was used for data binding is kinda deprecated / not used any more because it is not built into the standard Android tools.
If you're using Kotlin, the extended Android Kotlin plugin/tools also has some data-binding features built in.

RxJava has an RxKotlin flavor. For the json parsing, GSON can get weird with Kotlin. I haven't used Moshi but it is a newer library that is likely more up to date. It's built by Square (also known as Jake Wharton) just like  Retrofit. So it probably has better Kotlin support.

Picasso is another one by Square, still haven't used it. But we really liked Glide in ooVoo. Glide is built by a dude that works at Google, and is used in most of their apps. (edited)

Compare Butterknife to Dagger2

### DI

### Package Management

### Promises?

### Web Requests

### Unit Tests