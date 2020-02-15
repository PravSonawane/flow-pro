package ui.lib.utils

import androidx.lifecycle.MutableLiveData
import core.lib.analytics.Analytics

class AnalyticsMutableLiveData<T>(
    private val analyticsKey: String,
    private val analytics: Analytics
) : MutableLiveData<T>() {

    override fun setValue(value: T) {
        analytics.logEvent(analyticsKey, mapOf("onSetValue" to value as Any))
        super.setValue(value)
    }

    override fun postValue(value: T) {
        analytics.logEvent(analyticsKey, mapOf("onPostValue" to value as Any))
        super.postValue(value)
    }

    override fun getValue(): T? {
        val value = super.getValue()
        analytics.logEvent(analyticsKey, mapOf("onGetValue" to value as Any))
        return value
    }
}