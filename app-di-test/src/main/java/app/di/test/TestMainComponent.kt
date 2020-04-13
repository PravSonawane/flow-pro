package app.di.test

import android.app.Activity
import app.di.annotations.ActivityContext
import dagger.Component
import ui.lib.di.ActivityScope
import ui.lib.di.BaseMainComponent
import ui.lib.di.UiModule
import ui.navigation.di.NavigationComponent

/** Dagger test component interface */
@Component(
    dependencies = [TestAppComponent::class],
    modules = [
        UiModule::class,
        TestMainModule::class
    ]
)
@ActivityScope
interface TestMainComponent : BaseMainComponent, TestAppComponent, NavigationComponent {
    @ActivityContext fun activity(): Activity

    /** Dagger Builder for [TestMainComponent] */
    @Component.Builder
    interface Builder {
        fun appComponent(appComponent: TestAppComponent): Builder
        fun build(): TestMainComponent
    }
}
