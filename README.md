# DulyNoted-Android

Duly Noted is an app designed to help those learning piano or sight reading to associate notes with keys and positions on a musical staff.
In particular, this Android app was created as an experiment to minimize side effects in a non-trivial app.

The biggest source of side effects in an app environment is I/O; to mitigate this, I'll be using Litho, by Facebook.
Litho is a declarative UI library that encapsulates the UI/state coordination logic.
By passing just the model state to the components, we can avoid having to manipulate the view directly.

Aside from the view, it's (mostly) a matter of mapping old state to new state as "events" (user interaction, background task completion) occur.
By channeling state-changing events through a stream, we ensure that the state gets updated sequentially. This, in turn, eliminates a lot of concurrency-related issues.

## Goals and Guidelines:

- Stay functional, leaving side effects to libraries as much as possible
  - `val` instead of `var`
  - immutable collections and data structures
  - declarative UI
  - Functional Reactive Programming for data flows (communication across app areas)
- Mark any side effect with a `Side-effect!` comment, along with a justification
- Full unit test coverage on the model, as well as on complex Litho components
- Maintain best practices and patterns for Android or app dev in general

## Terminology:

- *Keyboard*: A full or partial piano keyboard

- *Note*: A single playable unit that can be represent

- *Note Position*: A position on a staff where a Note may appear

- *Note Value*: The duration of a note, in terms of fraction of a whole.

- *Octave*: A single band of Pitches, divided into the appropraite Pitch Classes

- *Pitch*: A frequency of music, representing one Pitch Class in one Octave

- *Pitch Class*: The "letter" portion of the Pitch - not Octave specific