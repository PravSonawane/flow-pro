package ui.lib.utils

import androidx.lifecycle.MutableLiveData
import core.lib.analytics.Analytics
import javax.inject.Inject

class LiveDataFactory @Inject constructor(
    private val analytics: Analytics
) {

    fun <T> mutableLiveData(analyticKey: String): MutableLiveData<T> {
        return AnalyticsMutableLiveData(analyticKey, analytics)
    }
}