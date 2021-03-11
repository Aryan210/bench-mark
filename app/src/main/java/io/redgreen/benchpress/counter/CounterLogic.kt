package io.redgreen.benchpress.counter

import com.spotify.mobius.Next
import com.spotify.mobius.Next.*
import com.spotify.mobius.Update
import io.reactivex.Observable
import java.util.*
import java.util.concurrent.TimeUnit

object CounterLogic : Update<CounterModel, CounterEvent, Nothing> {
    override fun update(
        model: CounterModel,
        event: CounterEvent
    ): Next<CounterModel, Nothing> { // 0/1 (Model update) - * (Effects)
        return when (event) {
            is StartClickedEvent -> if (model.currentState == CurrentState.IDLE) next(model) else next(model.updateStartedDetails())
            is PauseClickedEvent -> next<CounterModel, Nothing>(model.updatePausedDetails())
            is RestartClickedEvent -> next<CounterModel, Nothing>(model.updateRestartedDetails())
            is CountIncrementEvent -> if (model.currentState != CurrentState.PAUSED || (model.currentState == CurrentState.STARTED && model.counter!=0))
                next(model.updateCounter(event.count))
            else
                next(model)
            else -> TODO()
        }
    }
}
