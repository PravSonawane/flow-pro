package dev.curlybraces.flowpro

import androidx.appcompat.app.AppCompatActivity
import dagger.Component
import ui.lib.di.ActivityScope
import ui.lib.di.BaseMainComponent
import ui.lib.di.UiModule

/** Dagger component interface for an [AppCompatActivity] */
@Component(
    dependencies = [AppComponent::class],
    modules = [
        UiModule::class,
        MainVmModule::class,
        MainModule::class
    ]
)
@ActivityScope
interface MainComponent : BaseMainComponent {
    fun injectIn(mainActivity: MainActivity)

    /** Dagger Builder for [MainComponent] */
    @Component.Builder
    interface Builder {
        fun appComponent(appComponent: AppComponent): Builder
        fun mainModule(mainModule: MainModule): Builder
        fun build(): MainComponent
    }
}