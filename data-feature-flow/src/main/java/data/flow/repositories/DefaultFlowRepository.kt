package data.flow.repositories

import core.lib.result.Result
import core.lib.result.UnknownError
import domain.flow.models.Flow
import domain.flow.models.SaveFlowInput
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

    override fun save(input: SaveFlowInput): Observable<Result<Flow>> {
        val flow = if (dataStore[input.id] == null) {
            Flow(
                id = (++idCount).toString(),
                name = input.name
            )
        } else {
            Flow(
                id = dataStore[input.id]!!.id,
                name = input.name
            )
        }

        dataStore[flow.id] = flow

        return Observable.just(Result.OnSuccess(flow))
    }
}