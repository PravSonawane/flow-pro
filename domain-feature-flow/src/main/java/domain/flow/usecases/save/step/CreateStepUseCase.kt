package domain.flow.usecases.save.step

import core.lib.result.Result
import core.lib.usecase.ObservableResultUseCase
import domain.flow.repositories.StepRepository
import domain.models.flow.Step
import io.reactivex.Observable
import javax.inject.Inject

class CreateStepUseCase @Inject constructor(
    private val stepRepository: StepRepository
) : ObservableResultUseCase<CreateStepInput, Step> {

    override operator fun invoke(input: CreateStepInput): Observable<Result<Step>> {
        return stepRepository.create(input)
    }
}
