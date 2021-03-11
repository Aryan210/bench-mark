package io.redgreen.benchpress.counter

import com.spotify.mobius.test.NextMatchers.hasModel
import com.spotify.mobius.test.NextMatchers.hasNoEffects
import com.spotify.mobius.test.UpdateSpec
import com.spotify.mobius.test.UpdateSpec.assertThatNext
import org.junit.Test

class CounterLogicTest {
    private val updateSpec = UpdateSpec<CounterModel, CounterEvent, Nothing>(CounterLogic::update)

    @Test
    fun `when StartClickedEvent is consumed`() {
        val zeroModel = CounterModel.getInitialModel()
        val updatedModel = zeroModel.updateStartedDetails()
        updateSpec
            .given(zeroModel)
            .`when`(StartClickedEvent)
            .then(
                assertThatNext(
                    hasModel(updatedModel),
                    hasNoEffects()
                )
            )
    }

    @Test
    fun `when PauseClickedEvent is consumed`() {
        val zeroModel = CounterModel.getInitialModel()
        val updatedModel = zeroModel.updatePausedDetails()
        updateSpec
            .given(zeroModel)
            .`when`(PauseClickedEvent)
            .then(
                assertThatNext(
                    hasModel(updatedModel),
                    hasNoEffects()
                )
            )
    }

    @Test
    fun `when ResetClickedEvent is consumed`() {
        val zeroModel = CounterModel.getInitialModel()
        val updatedModel = zeroModel.updateRestartedDetails()
        updateSpec
            .given(zeroModel)
            .`when`(RestartClickedEvent)
            .then(
                assertThatNext(
                    hasModel(updatedModel),
                    hasNoEffects()
                )
            )
    }
}
