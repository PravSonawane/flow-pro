package ui.feature.flow.selectnode

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ui.lib.di.ViewModelKey

@Module
abstract class SelectNodeViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(SelectNodeViewModel::class)
    abstract fun bindViewModel(viewModel: SelectNodeViewModel): ViewModel
}
