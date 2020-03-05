package app.di.test

import android.app.Application
import android.content.Context
import app.di.annotations.ApplicationContext
import core.lib.analytics.di.AnalyticsComponent
import core.lib.plugin.di.PluginComponent
import dagger.BindsInstance
import dagger.Component
import domain.flow.di.FlowComponent
import ui.lib.di.BaseAppComponent
import javax.inject.Singleton

@Component(
    modules = [
        TestAppModule::class
    ]
)
@Singleton
interface TestAppComponent : BaseAppComponent,
    PluginComponent,
    AnalyticsComponent,
    FlowComponent {

    override fun application(): Application
    @ApplicationContext
    override fun applicationContext(): Context

    /** Dagger Builder for [TestAppComponent] */
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        @BindsInstance
        fun applicationContext(@ApplicationContext context: Context): Builder
        fun build(): TestAppComponent
    }
}
