package io.redgreen.benchpress.counter

import android.os.Handler
import io.redgreen.benchpress.counter.CurrentState.*


class CounterViewRenderer(
    private val view: CounterView
) {
    fun render(model: CounterModel) {
        when(model.currentState) {
            STARTED -> showStartedState(model.counter)
            PAUSED -> showPausedState(model.counter)
            RESTARTED -> showRestartedState(model.counter)
            IDLE -> showIdleState(model.counter)
        }
    }

    private fun showIdleState(counter: Int) {
        view.idleCounter(counter)
    }

    private fun showRestartedState(counter:Int) {
        view.restartCounter(counter)
    }

    private fun showPausedState(counter: Int) {
        view.pauseCounter(counter)
    }

    private fun showStartedState(counter: Int) {
        view.startCounter(counter)
    }
}
