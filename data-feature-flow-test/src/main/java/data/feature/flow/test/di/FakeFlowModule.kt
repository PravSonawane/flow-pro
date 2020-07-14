package data.feature.flow.test.di

import dagger.Module
import dagger.Provides
import domain.flow.di.FlowModule
import domain.flow.repositories.FlowRepository
import domain.flow.repositories.NodeRepository
import domain.flow.repositories.StepRepository

@Module(includes = [FlowModule::class])
class FakeFlowModule {

    @Provides
    fun provideFlowRepository(): FlowRepository {
        return FakeFlowRepository()
    }

    @Provides
    fun provideNodeRepository(): NodeRepository {
        return FakeNodeRepository()
    }

    @Provides
    fun provideStepRepository(): StepRepository {
        return FakeStepRepository()
    }
}
