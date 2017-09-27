package com.brandonwilliamscs.apputil

import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject

/**
 * A thread-safe state machine based on ReactiveX streams.
 * Translates view- and model-initiated events into state changes. It observes model events as a stream of streams
 *  to facilitate timed or promised delays in the event flow. Combines (multiplexes) events from both sources before
 *  eventually emitting a sequence of states.
 * Created by Brandon on 9/26/2017.
 * @param initialState the start state, before any transitions occur
 * @param stateTransition a function to transform old to new state based on the incoming event
 * @property modelEventObserver observes streams of model events
 * @property viewEventObserver observes individual view events
 * @property stateChangeObservable emits new states for each event that comes in
 */
class StateMachine<S, V, M>(initialState: S, stateTransition: (S, MultiplexedEvent<V, M>) -> S) {

    val modelEventObserver: Observer<Observable<M>>
    val viewEventObserver: Observer<V>
    val stateChangeObservable: Observable<S>

    private val muxedEventStream: Observable<MultiplexedEvent<V, M>>

    init {
        // start by creating a subject for each kind of event.
        var modelEventSubjectOfStream = PublishSubject.create<Observable<M>>()
        this.modelEventObserver = modelEventSubjectOfStream

        var viewEventSubject = PublishSubject.create<V>()
        this.viewEventObserver = viewEventSubject

        // multiplex the streams so there's a single event stream.
        // flatten the model stream first in a way that allows interleaving.
        val muxedModelStream = modelEventSubjectOfStream
                .subscribeOn(Schedulers.computation())
                .flatMap { it }
                .map { MultiplexedEvent<V, M>(null, it) }
        val muxedViewStream = viewEventSubject
                .subscribeOn(Schedulers.computation())
                .map { MultiplexedEvent<V, M>(it, null) }
        muxedEventStream = muxedModelStream.mergeWith(muxedViewStream)

        // compute the state stream by repeatedly applying the transition to each prior state.
        stateChangeObservable = muxedEventStream.scan(initialState, stateTransition)
    }
}

/**
 * Stores either a view event or a model event, pluse a field to indicate which.
 * A poor man's variant record. It's a little unwieldy, but it's easily convertible.
 */
class MultiplexedEvent<V, M>(private val viewEvent: V?, private val modelEvent: M?) {
    val isViewEvent = viewEvent != null
    val fromView: V get() = viewEvent!!
    val fromModel: M get() = modelEvent!!
}
