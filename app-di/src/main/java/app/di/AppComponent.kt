package app.di

import android.app.Application
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
interface AppComponent : BaseAppComponent, FlowComponent {

    override fun application(): Application

    /** Dagger Builder for [AppComponent] */
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }
}