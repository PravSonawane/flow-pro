package core.lib.analytics.di

import core.lib.analytics.Analytics
import core.lib.analytics.AnalyticsRepository

interface AnalyticsComponent {
    fun analytics(): Analytics
    fun analyticsRepository(): AnalyticsRepository
}
