package io.redgreen.benchpress.counter

interface CounterView {
    fun startCounter(counter: Int)
    fun pauseCounter(counter: Int)
    fun restartCounter(counter: Int)
    fun idleCounter(counter: Int)
}
