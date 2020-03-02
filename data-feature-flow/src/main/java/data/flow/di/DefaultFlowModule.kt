package data.flow.di

import dagger.Module
import dagger.Provides
import data.flow.repositories.DefaultFlowRepository
import data.flow.repositories.DefaultNodeRepository
import data.flow.repositories.DefaultStepRepository
import domain.flow.repositories.FlowRepository
import domain.flow.repositories.NodeRepository
import domain.flow.repositories.StepRepository
import javax.inject.Singleton

@Module(includes = [FlowModule::class])
class DefaultFlowModule {

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

    @Singleton
    @Provides
    fun provideStepRepository(): StepRepository {
        return DefaultStepRepository()
    }
}
