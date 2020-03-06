package ui.feature.flow.list

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ui.lib.di.ViewModelKey

@Module
abstract class FlowListViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(FlowListViewModel::class)
    abstract fun bindViewModel(viewModel: FlowListViewModel): ViewModel
}
