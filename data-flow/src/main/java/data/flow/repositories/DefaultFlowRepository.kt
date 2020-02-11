package data.flow.repositories

import domain.core.result.Result
import domain.core.result.UnknownError
import domain.flow.models.Flow
import domain.flow.models.SaveFlowRequest
import domain.flow.repositories.FlowRepository
import io.reactivex.Observable

class DefaultFlowRepository : FlowRepository {

    private var idCount = 0
    private val dataStore = HashMap<String, Flow>()

    override fun get(id: String): Observable<Result<Flow>> {
        return if (dataStore[id] == null) {
            Observable.just(Result.OnError(UnknownError()))
        } else {
            Observable.just(Result.OnSuccess(dataStore[id]!!))
        }
    }

    override fun save(request: SaveFlowRequest): Observable<Result<Flow>> {
        val flow = if (dataStore[request.id] == null) {
            Flow(
                id = (++idCount).toString(),
                name = request.name
            )
        } else {
            Flow(
                id = dataStore[request.id]!!.id,
                name = request.name
            )
        }

        dataStore[flow.id] = flow

        return Observable.just(Result.OnSuccess(flow))
    }
}