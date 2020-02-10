package domain.flow.usecases

import domain.core.Result
import domain.flow.models.Flow
import domain.flow.models.SaveFlowRequest
import domain.flow.repositories.FlowRepository
import io.reactivex.Observable
import javax.inject.Inject

class GetFlowByIdUseCase @Inject constructor(
    private val flowRepository: FlowRepository
) {

    operator fun invoke(id: String): Observable<Result<Flow>> {
        return flowRepository.get(id)
    }
}