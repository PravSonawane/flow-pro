package ui.lib.base


import androidx.lifecycle.ViewModel
import io.reactivex.Observable
import ui.lib.utils.AnalyticsStream
import ui.lib.utils.StreamFactory

abstract class BaseViewModel<Input, Output>(
    analyticsKey: String,
    streamFactory: StreamFactory,
    private val inputStream: AnalyticsStream<Input> = streamFactory.analyticsStream(analyticsKey),
    private val eventStream: AnalyticsStream<Output> = streamFactory.analyticsStream(analyticsKey)
) : ViewModel() {

    fun sendInput(input: Input) {
        inputStream.publish(input)
    }
    
    protected fun observeInput(): Observable<Input> {
        return inputStream.subscribe()
    }

    protected fun sendOutput(event: Output) {
        eventStream.publish(event)
    }

    fun observeOutput(): Observable<Output> {
        return eventStream.subscribe()
    }

    abstract fun dispose()
}