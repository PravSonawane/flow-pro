package ui.navigation.usecases

import core.lib.result.Result
import core.lib.result.toResult
import core.lib.usecase.ObservableResultUseCase
import io.reactivex.Observable
import ui.navigation.NavigationConfig
import ui.navigation.Navigator
import javax.inject.Inject

class NavigationUseCase @Inject constructor(
    private val navigator: Navigator
) : ObservableResultUseCase<NavigationConfig, String> {

    override fun invoke(input: NavigationConfig): Observable<Result<String>> {
        return Observable.just(input)
            .map { navigator.navigate(input) }
            .map { it.toResult() }
    }

    companion object {
        const val NAMED = "ui.navigation.usecases.NavigationUseCase"
        const val ANALYTICS_KEY = "b1957339-ddef"
        const val PLUGIN_KEY = "e74ea9a6-af53"
    }
}
