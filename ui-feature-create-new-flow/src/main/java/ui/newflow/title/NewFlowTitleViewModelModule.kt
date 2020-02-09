package ui.newflow.title

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ui.lib.di.ViewModelKey

@Module
abstract class NewFlowTitleViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(NewFlowTitleViewModel::class)
    abstract fun bindViewModel(viewModel: NewFlowTitleViewModel): ViewModel
}