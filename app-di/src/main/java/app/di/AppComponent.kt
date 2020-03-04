package app.di

import android.app.Application
import android.content.Context
import app.di.annotations.ApplicationContext
import core.lib.analytics.di.AnalyticsComponent
import core.lib.plugin.di.PluginComponent
import dagger.BindsInstance
import dagger.Component
import data.flow.di.FlowComponent
import ui.lib.di.BaseAppComponent
import javax.inject.Singleton

@Component(
    modules = [
        AppModule::class
    ]
)
@Singleton
interface AppComponent : BaseAppComponent,
    PluginComponent,
    AnalyticsComponent,
    FlowComponent {

    override fun application(): Application
    @ApplicationContext override fun applicationContext(): Context

    /** Dagger Builder for [AppComponent] */
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        @BindsInstance
        fun applicationContext(@ApplicationContext context: Context): Builder
        fun build(): AppComponent
    }
}
