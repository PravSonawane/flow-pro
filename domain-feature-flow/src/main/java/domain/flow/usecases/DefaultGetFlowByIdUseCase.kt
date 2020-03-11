package domain.flow.usecases

import core.lib.result.Result
import core.lib.result.toData
import core.lib.result.toResult
import core.lib.usecase.common.AnalyticsData
import core.lib.usecase.common.BusinessData
import core.lib.usecase.common.InputAnalyticsTransformer
import core.lib.usecase.common.OutputAnalyticsTransformer
import core.lib.usecase.common.PluginData
import core.lib.usecase.common.PluginTransformer
import domain.flow.repositories.FlowRepository
import domain.models.flow.Flow
import io.reactivex.Observable
import javax.inject.Inject

class DefaultGetFlowByIdUseCase @Inject constructor(
    private val pluginTransformer: PluginTransformer<String>,
    private val inputAnalyticsTransformer: InputAnalyticsTransformer<String>,
    private val outputAnalyticsTransformer: OutputAnalyticsTransformer<Flow>,
    flowRepository: FlowRepository
) : GetFlowByIdUseCase(flowRepository) {

    override operator fun invoke(input: BusinessData<String>): Observable<Result<Flow>> {
        return Observable.just(PluginData(input.plugin, input.data))
            .compose(pluginTransformer)
            .flatMap {
                Observable.just(AnalyticsData(input.analyticsKey, input.data))
            }
            .compose(inputAnalyticsTransformer)
            .flatMap { super.invoke(input) }
            .flatMap { it.toData() }
            .map { AnalyticsData(input.analyticsKey, it) }
            .compose(outputAnalyticsTransformer)
            .map { it.toResult() }
    }
}
