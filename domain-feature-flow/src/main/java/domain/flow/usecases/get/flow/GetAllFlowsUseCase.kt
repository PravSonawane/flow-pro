package domain.flow.usecases.get.flow

import core.lib.result.Result
import core.lib.usecase.ObservableResultUseCase
import domain.flow.repositories.FlowRepository
import domain.models.flow.Flow
import io.reactivex.Observable
import javax.inject.Inject

typealias GetAllFlowsUseCase = ObservableResultUseCase<Unit, List<Flow>>

class GetAllFlowsUseCaseInternal @Inject constructor(
    private val flowRepository: FlowRepository
) : ObservableResultUseCase<Unit, List<Flow>> {

    override operator fun invoke(input: Unit): Observable<Result<List<Flow>>> {
        return flowRepository.getAll()
    }

    companion object {
        const val ANALYTICS_KEY = "58993dea-6ddf"
        const val PLUGIN_KEY = "3f39506e-6669"
    }
}
