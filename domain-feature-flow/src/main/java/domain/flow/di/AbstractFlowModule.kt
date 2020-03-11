package domain.flow.di

import dagger.Binds
import dagger.Module
import domain.flow.usecases.DefaultGetFlowByIdUseCase
import domain.flow.usecases.GetFlowByIdUseCase

@Module
abstract class AbstractFlowModule {

    @Binds
    abstract fun provideGetFlowById(getFlowByIdUseCase: DefaultGetFlowByIdUseCase): GetFlowByIdUseCase
}