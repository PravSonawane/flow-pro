package core.lib.analytics.di

import core.lib.analytics.Analytics

interface AnalyticsComponent {
    fun analytics(): Analytics
}
