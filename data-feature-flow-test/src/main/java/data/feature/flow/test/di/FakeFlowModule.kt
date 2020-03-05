package data.feature.flow.test.di

import dagger.Module
import dagger.Provides
import domain.flow.di.FlowModule
import domain.flow.repositories.FlowRepository
import domain.flow.repositories.NodeRepository
import domain.flow.repositories.StepRepository
import javax.inject.Singleton

@Module(includes = [FlowModule::class])
class FakeFlowModule {

    @Singleton
    @Provides
    fun provideFlowRepository(): FlowRepository {
        return FakeFlowRepository()
    }

    @Singleton
    @Provides
    fun provideNodeRepository(): NodeRepository {
        return FakeNodeRepository()
    }

    @Singleton
    @Provides
    fun provideStepRepository(): StepRepository {
        return FakeStepRepository()
    }
}
