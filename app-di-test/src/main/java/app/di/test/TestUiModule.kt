package app.di.test

import dagger.Binds
import dagger.Module

@Module
abstract class TestUiModule {
    @Binds
    abstract fun bindViewModelFactory(factory: TestAppViewModelFactory): TestAppViewModelFactory
}
