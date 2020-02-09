package dev.curlybraces.flowpro

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import ui.lib.di.BaseAppComponent
import javax.inject.Singleton

@Component(
    modules = [
        AppModule::class
    ]
)
@Singleton
interface AppComponent : BaseAppComponent {

    override fun application(): Application

    /** Dagger Builder for [AppComponent] */
    @Component.Builder interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }
}