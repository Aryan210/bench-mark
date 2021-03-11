package io.redgreen.benchpress.counter

import android.content.Context
import android.content.Intent
import com.spotify.mobius.Next
import io.reactivex.ObservableTransformer
import io.redgreen.benchpress.R
import io.redgreen.benchpress.architecture.android.BaseActivity
import io.redgreen.benchpress.architecture.effecthandlers.NoOpEffectHandler
import kotlinx.android.synthetic.main.counter_activity.*
import java.util.logging.Handler

class CounterActivity : BaseActivity<CounterModel, CounterEvent, Nothing>(), CounterView {
  companion object {
    fun start(context: Context) {
      context.startActivity(Intent(context, CounterActivity::class.java))
    }
  }

  private val renderer by lazy {
    CounterViewRenderer(this)
  }

  override fun layoutResId(): Int {
    return R.layout.counter_activity
  }

  override fun setup() {
    startButton.setOnClickListener { eventSource.notifyEvent(StartClickedEvent) }
    resetButton.setOnClickListener { eventSource.notifyEvent(RestartClickedEvent) }
    pauseButton.setOnClickListener { eventSource.notifyEvent(PauseClickedEvent) }
  }

  override fun initialModel(): CounterModel {
    return CounterModel.getInitialModel()
  }

  override fun updateFunction(
    model: CounterModel,
    event: CounterEvent
  ): Next<CounterModel, Nothing> {
    return CounterLogic.update(model, event)
  }

  override fun render(model: CounterModel) {
    renderer.render(model)
    counterTextView.text = model.counter.toString()
  }

  override fun effectHandler(): ObservableTransformer<Nothing, CounterEvent> {
    return NoOpEffectHandler()
  }

  override fun startCounter(counter: Int) {
    eventSource.notifyEvent(CountIncrementEvent(counter+1))
  }

  override fun pauseCounter(counter: Int) {
    /*no-op*/
  }

  override fun restartCounter(counter: Int) {
    eventSource.notifyEvent(CountIncrementEvent(counter+1))
  }

  override fun idleCounter(counter: Int) {
    android.os.Handler().postDelayed(
            Runnable{eventSource.notifyEvent(CountIncrementEvent(counter+1))}
            ,1000
    )
  }
}
