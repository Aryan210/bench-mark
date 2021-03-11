package io.redgreen.benchpress.counter

sealed class CounterEvent

object StartClickedEvent : CounterEvent()

object PauseClickedEvent : CounterEvent()

object RestartClickedEvent : CounterEvent()

data class CountIncrementEvent(
    val count: Int
) : CounterEvent()