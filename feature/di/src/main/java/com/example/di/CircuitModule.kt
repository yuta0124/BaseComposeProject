package com.example.di

import com.slack.circuit.foundation.Circuit
import com.slack.circuit.runtime.presenter.Presenter
import com.slack.circuit.runtime.ui.Ui
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
object CircuitModule {
    @Provides
    fun provideCircuit(
        uiFactories: Set<@JvmSuppressWildcards Ui.Factory>,
        presenterFactories: Set<@JvmSuppressWildcards Presenter.Factory>,
    ): Circuit = Circuit.Builder().addUiFactories(uiFactories, presenterFactories).build()

    private fun Circuit.Builder.addUiFactories(
        uiFactories: Set<Ui.Factory>,
        presenterFactories: Set<Presenter.Factory>,
    ) = apply {
        uiFactories.forEach {
            addUiFactory(it)
        }

        presenterFactories.forEach {
            addPresenterFactory(it)
        }
    }
}
