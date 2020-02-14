package ui.newflow.createstep

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ui.lib.di.ViewModelKey

@Module
abstract class CreateStepViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(CreateStepViewModel::class)
    abstract fun bindViewModel(viewModel: CreateStepViewModel): ViewModel
}