package core.lib.experimentation.test.di

import core.lib.experimentation.ExperimentationRepository
import core.lib.experimentation.di.ExperimentationModule
import dagger.Module
import dagger.Provides

@Module(includes = [ExperimentationModule::class])
class FakeExperimentationModule {

    @Provides
    fun experimentationRepository(): ExperimentationRepository {
        return FakeExperimentationRepository()
    }
}
