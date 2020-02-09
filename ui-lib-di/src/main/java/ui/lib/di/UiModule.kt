package ui.lib.di

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import ui.lib.di.AppViewModelFactory

@Module
abstract class UiModule {
    @Binds
    abstract fun bindViewModelFactory(factory: AppViewModelFactory): ViewModelProvider.Factory
}