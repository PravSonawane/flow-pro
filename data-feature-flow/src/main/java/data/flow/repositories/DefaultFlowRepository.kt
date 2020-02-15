package data.flow.repositories

import core.lib.result.Result
import core.lib.result.UnknownError
import domain.flow.models.SaveFlowInput
import domain.flow.repositories.FlowRepository
import domain.models.flow.Flow
import io.reactivex.Observable

class DefaultFlowRepository : FlowRepository {

    private val dataStore = HashMap<String, Flow>()

    override fun get(id: String): Observable<Result<Flow>> {
        return if (dataStore[id] == null) {
            Observable.just(Result.OnError(UnknownError()))
        } else {
            Observable.just(Result.OnSuccess(dataStore[id]!!))
        }
    }

    override fun save(input: SaveFlowInput): Observable<Result<Flow>> {
        val flow = Flow(
            id = "1",
            name = input.name,
            state = Flow.State.DRAFT
        )

        dataStore["1"] = flow

        return Observable.just(Result.OnSuccess(flow))
    }
}
