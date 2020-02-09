package ui.newflow.selectnode

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ui.lib.di.ViewModelKey
import ui.newflow.selectnode.NewFlowSelectNodeViewModel

@Module
abstract class NewFlowSelectNodeViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(NewFlowSelectNodeViewModel::class)
    abstract fun bindViewModel(viewModel: NewFlowSelectNodeViewModel): ViewModel
}