package domain.flow.usecases

import core.lib.result.Result
import core.lib.usecase.ObservableResultUseCase
import domain.flow.repositories.StepRepository
import domain.models.flow.Step
import io.reactivex.Observable
import javax.inject.Inject

class GetCurrentInputStepsUseCase @Inject constructor(
    private val stepRepository: StepRepository
) : ObservableResultUseCase<GetInputStepsInput, List<Step>> {

    override fun invoke(input: GetInputStepsInput): Observable<Result<List<Step>>> {
        return stepRepository.getCurrentInputSteps(input.stepId)
    }

    companion object {
        const val NAMED = "GET_CURRENT_INPUT_STEPS"
        const val ANALYTICS_KEY = "d5869728-eded"
        const val PLUGIN_KEY = "8ef2e492-f438"
    }
}
data class GetInputStepsInput(
    val stepId: String
)
