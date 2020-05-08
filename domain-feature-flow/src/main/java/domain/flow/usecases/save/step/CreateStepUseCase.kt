package domain.flow.usecases.save.step

import core.lib.result.Result
import core.lib.usecase.ObservableResultUseCase
import domain.flow.repositories.StepRepository
import domain.models.flow.Step
import io.reactivex.Observable
import javax.inject.Inject

typealias CreateStepUseCase = ObservableResultUseCase<CreateStepInput, Step>

class CreateStepUseCaseInternal @Inject constructor(
    private val stepRepository: StepRepository
) : ObservableResultUseCase<CreateStepInput, Step> {

    override operator fun invoke(input: CreateStepInput): Observable<Result<Step>> {
        return stepRepository.create(input)
    }

    companion object {
        const val ANALYTICS_KEY = "6fefbb04-d435"
        const val PLUGIN_KEY = "71545e2b-0388"
    }
}
