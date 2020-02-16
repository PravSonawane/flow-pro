package ui.feature.flow.steplist

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ui.lib.di.ViewModelKey

@Module
abstract class FlowStepListViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(FlowStepListViewModel::class)
    abstract fun bindViewModel(viewModel: FlowStepListViewModel): ViewModel
}
