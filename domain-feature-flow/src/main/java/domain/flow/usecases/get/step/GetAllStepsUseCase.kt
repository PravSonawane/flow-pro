package domain.flow.usecases.get.step

import core.lib.result.Result
import core.lib.usecase.ObservableResultUseCase
import domain.flow.repositories.StepRepository
import domain.models.flow.Step
import io.reactivex.Observable
import javax.inject.Inject

typealias GetAllStepsUseCase = ObservableResultUseCase<GetAllStepsInput, List<Step>>

class GetAllStepsFunction @Inject constructor(
    private val stepRepository: StepRepository
) : GetAllStepsUseCase {

    override fun invoke(input: GetAllStepsInput): Observable<Result<List<Step>>> {
        return stepRepository.getAll(input.flowId)
    }

    companion object {
        const val ANALYTICS_KEY = "42ec4f7f-d032"
        const val PLUGIN_KEY = "2d654511-3fa2"
    }
}

data class GetAllStepsInput(
    val flowId: String
)
