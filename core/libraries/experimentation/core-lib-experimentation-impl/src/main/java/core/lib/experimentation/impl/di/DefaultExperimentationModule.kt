package core.lib.experimentation.impl.di

import core.lib.experimentation.ExperimentationRepository
import core.lib.experimentation.di.ExperimentationModule
import core.lib.experimentation.impl.DefaultExperimentationRepository
import dagger.Module
import dagger.Provides

@Module(includes = [ExperimentationModule::class])
class DefaultExperimentationModule {

    @Provides
    fun experimentationRepository(): ExperimentationRepository {
        return DefaultExperimentationRepository()
    }
}
