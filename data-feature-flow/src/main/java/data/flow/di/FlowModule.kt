package data.flow.di

import dagger.Module
import dagger.Provides
import data.flow.repositories.DefaultFlowRepository
import data.flow.repositories.DefaultNodeRepository
import domain.flow.repositories.FlowRepository
import domain.flow.repositories.NodeRepository
import javax.inject.Singleton

@Module
class FlowModule {

    @Singleton
    @Provides
    fun provideFlowRepository(): FlowRepository {
        return DefaultFlowRepository()
    }

    @Singleton
    @Provides
    fun provideNodeRepository(): NodeRepository {
        return DefaultNodeRepository()
    }
}
