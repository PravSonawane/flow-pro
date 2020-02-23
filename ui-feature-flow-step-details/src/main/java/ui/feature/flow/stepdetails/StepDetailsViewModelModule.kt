package ui.feature.flow.stepdetails

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ui.lib.di.ViewModelKey

@Module
abstract class StepDetailsViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(StepDetailsViewModel::class)
    abstract fun bindViewModel(viewModel: StepDetailsViewModel): ViewModel
}
