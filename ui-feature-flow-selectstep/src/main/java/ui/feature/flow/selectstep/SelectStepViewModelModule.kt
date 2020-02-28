package ui.feature.flow.selectstep

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ui.lib.di.ViewModelKey

@Module
abstract class SelectStepViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(SelectStepViewModel::class)
    abstract fun bindViewModel(viewModel: SelectStepViewModel): ViewModel
}
