package domain.flow.usecases

import core.lib.result.Result
import core.lib.usecase.ObservableResultUseCase
import domain.flow.repositories.StepRepository
import domain.models.flow.Step
import io.reactivex.Observable
import javax.inject.Inject

class GetPossibleOutputStepsUseCase @Inject constructor(
    private val stepRepository: StepRepository
) : ObservableResultUseCase<GetPossibleOutputStepsInput, List<Step>> {

    override fun invoke(input: GetPossibleOutputStepsInput): Observable<Result<List<Step>>> {
        return stepRepository.getPossibleOutputSteps(input.stepId)
    }

    companion object {
        const val NAMED = "GET_POSSIBLE_INPUT_STEPS"
    }
}
data class GetPossibleOutputStepsInput(
    val stepId: String
)
