package domain.flow.usecases

import core.lib.result.Result
import core.lib.usecase.ObservableResultUseCase
import domain.flow.repositories.StepRepository
import domain.models.flow.Step
import io.reactivex.Observable
import javax.inject.Inject

class GetAllStepsUseCase @Inject constructor(
    private val stepRepository: StepRepository
) : ObservableResultUseCase<GetAllStepsInput, List<Step>> {

    override fun invoke(input: GetAllStepsInput): Observable<Result<List<Step>>> {
        return stepRepository.getAll(input.flowId)
    }

    companion object {
        const val NAMED = "GET_ALL_INPUT_STEPS"
        const val ANALYTICS_KEY = "42ec4f7f-d032"
        const val PLUGIN_KEY = "2d654511-3fa2"
    }
}

data class GetAllStepsInput(
    val flowId: String
)
