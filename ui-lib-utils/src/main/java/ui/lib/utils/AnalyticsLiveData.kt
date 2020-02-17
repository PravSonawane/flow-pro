package ui.lib.utils

import androidx.lifecycle.LiveData
import core.lib.analytics.Analytics

class AnalyticsLiveData<T>(
    private val analyticsKey: String,
    private val analytics: Analytics,
    private val defaultValue: T? = null
) : LiveData<T>(defaultValue) {

    override fun setValue(value: T) {
        val attributes: Map<String, String> = mapOf(
            "analyticsKey" to analyticsKey,
            "onSetValue" to (value as Any).toString()
        )
        analytics.logEvent(Analytics.KEY_DEBUG, attributes)
        super.setValue(value)
    }

    override fun postValue(value: T) {
        val attributes: Map<String, String> = mapOf(
            "analyticsKey" to analyticsKey,
            "onPostValue" to (value as Any).toString()
        )
        analytics.logEvent(Analytics.KEY_DEBUG, attributes)
        super.postValue(value)
    }

    override fun getValue(): T? {
        val value = super.getValue()

        val attributes: Map<String, String> = mapOf(
            "analyticsKey" to analyticsKey,
            "onGetValue" to (value as Any?).toString()
        )
        analytics.logEvent(Analytics.KEY_DEBUG, attributes)
        return value
    }
}
