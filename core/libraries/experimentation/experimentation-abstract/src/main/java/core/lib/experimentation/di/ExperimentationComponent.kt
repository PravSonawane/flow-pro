package core.lib.experimentation.di

import core.lib.experimentation.ExperimentationRepository

interface ExperimentationComponent {
    fun experimentationRepository(): ExperimentationRepository
}
