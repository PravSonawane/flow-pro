package app.di.test

import dagger.Component
import ui.lib.di.ActivityScope
import ui.lib.di.BaseMainComponent
import ui.lib.di.UiModule

/** Dagger test component interface */
@Component(
    dependencies = [TestAppComponent::class],
    modules = [
        UiModule::class
    ]
)
@ActivityScope
interface TestMainComponent : BaseMainComponent, TestAppComponent {

    /** Dagger Builder for [TestMainComponent] */
    @Component.Builder
    interface Builder {
        fun appComponent(appComponent: TestAppComponent): Builder
        fun build(): TestMainComponent
    }
}
