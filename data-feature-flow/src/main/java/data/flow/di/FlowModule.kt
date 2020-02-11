package data.flow.di

import dagger.Module
import dagger.Provides
import data.flow.repositories.DefaultFlowRepository
import domain.flow.repositories.FlowRepository
import javax.inject.Singleton

@Module
class FlowModule {

    @Singleton
    @Provides
    fun provideFlowRepository(): FlowRepository {
        return DefaultFlowRepository()
    }
}