package domain.flow.usecases

import core.lib.result.Result
import core.lib.usecase.ObservableResultUseCase
import domain.flow.repositories.FlowRepository
import domain.models.flow.Flow
import io.reactivex.Observable
import javax.inject.Inject

class GetFlowByIdUseCase @Inject constructor(
    private val flowRepository: FlowRepository
) : ObservableResultUseCase<String, Flow> {

    override operator fun invoke(input: String): Observable<Result<Flow>> {
        return flowRepository.get(input)
    }
}
