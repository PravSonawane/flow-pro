package domain.flow.usecases.get.step

import core.lib.result.Result
import core.lib.usecase.ObservableResultUseCase
import domain.flow.repositories.StepRepository
import domain.models.flow.Step
import io.reactivex.Observable
import javax.inject.Inject

typealias GetPossibleOutputStepsUseCase = ObservableResultUseCase<GetPossibleOutputStepsInput, List<Step>>

class GetPossibleOutputStepsUseCaseInternal @Inject constructor(
    private val stepRepository: StepRepository
) : ObservableResultUseCase<GetPossibleOutputStepsInput, List<Step>> {

    override fun invoke(input: GetPossibleOutputStepsInput): Observable<Result<List<Step>>> {
        return stepRepository.getPossibleOutputSteps(input.stepId)
    }

    companion object {
        const val ANALYTICS_KEY = "3c3decb8-37f1"
        const val PLUGIN_KEY = "badd98f1-bc99"
    }
}
data class GetPossibleOutputStepsInput(
    val stepId: String
)
