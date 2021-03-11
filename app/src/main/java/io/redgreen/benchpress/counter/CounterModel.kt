package io.redgreen.benchpress.counter

import android.os.Parcelable
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.functions.Consumer
import kotlinx.android.parcel.Parcelize
import java.util.concurrent.TimeUnit

@Parcelize
data class CounterModel(
    val counter: Int,
    val currentState: CurrentState
) : Parcelable {

    companion object{
        fun getInitialModel(): CounterModel = CounterModel(0,CurrentState.PAUSED)
    }
    fun updateStartedDetails() : CounterModel{
        val obj = copy(currentState = CurrentState.STARTED)
        return obj
    }

    fun updateCounter(counter: Int): CounterModel {
        return copy(counter = counter,currentState = CurrentState.IDLE)
    }

    fun updatePausedDetails() : CounterModel{
      return copy(currentState = CurrentState.PAUSED)
    }
    fun updateRestartedDetails() : CounterModel {
        val obj = copy(currentState = CurrentState.RESTARTED, counter = 0)
        return obj
    }
}
