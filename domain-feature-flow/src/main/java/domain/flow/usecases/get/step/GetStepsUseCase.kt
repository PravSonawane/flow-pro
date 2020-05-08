package domain.flow.usecases.get.step

import core.lib.result.DomainError
import core.lib.result.Result
import core.lib.result.toResult
import core.lib.usecase.ObservableResultUseCase
import domain.models.flow.Step
import domain.models.flow.StepType
import io.reactivex.Observable
import javax.inject.Inject

typealias GetStepsUseCase = ObservableResultUseCase<GetStepsInput, List<Step>>

class GetStepsUseCaseInternal @Inject constructor(
    private val getAllStepsUseCase: GetAllStepsUseCase,
    private val getCurrentInputStepsUseCase: GetCurrentInputStepsUseCase,
    private val getCurrentOutputStepsUseCase: GetCurrentOutputStepsUseCase
) : ObservableResultUseCase<GetStepsInput, List<Step>> {

    override fun invoke(input: GetStepsInput): Observable<Result<List<Step>>> {
        return Observable.just(input)
            .flatMap { getInputStepsByStepType(it) }
    }

    private fun getInputStepsByStepType(input: GetStepsInput): Observable<Result<List<Step>>> {
        return when {
            input.stepId == null -> getAllStepsUseCase(
                GetAllStepsInput(
                    input.flowId
                )
            )
            input.stepType == StepType.INPUT -> getCurrentInputStepsUseCase(
                GetInputStepsInput(
                    input.stepId
                )
            )
            input.stepType == StepType.OUTPUT -> getCurrentOutputStepsUseCase(
                GetOutputStepsInput(
                    input.stepId
                )
            )
            else -> invalidInputError(input).toResult()
        }
    }

    private fun invalidInputError(input: GetStepsInput): DomainError {
        return DomainError("fa53026f-e8a8", "Cannot handle input:$input")
    }

    companion object {
        const val ANALYTICS_KEY = "41508dfb-95b4"
        const val PLUGIN_KEY = "23145985-698d"
    }
}

data class GetStepsInput(
    val flowId: String,
    val stepId: String? = null,
    val stepType: StepType? = null
)
