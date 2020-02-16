package ui.lib.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import core.lib.analytics.Analytics
import javax.inject.Inject

class LiveDataFactory @Inject constructor(
    private val analytics: Analytics
) {

    fun <T> liveData(analyticKey: String, defaultValue: T? = null): LiveData<T> {
        return AnalyticsLiveData(analyticKey, analytics, defaultValue)
    }

    fun <T> mutableLiveData(analyticKey: String, defaultValue: T? = null): MutableLiveData<T> {
        return AnalyticsMutableLiveData(analyticKey, analytics, defaultValue)
    }
}
