package app.di

import dagger.Component
import ui.lib.base.BaseTest
import ui.lib.di.ActivityScope
import ui.lib.di.BaseMainComponent
import ui.lib.di.UiModule

/** Dagger test component interface */
@Component(
    dependencies = [TestAppComponent::class],
    modules = [
        UiModule::class,
        MainVmModule::class,
        MainModule::class
    ]
)
@ActivityScope
interface TestMainComponent : BaseMainComponent, TestAppComponent {
    fun injectIn(test: BaseTest)

    /** Dagger Builder for [TestMainComponent] */
    @Component.Builder
    interface Builder {
        fun appComponent(appComponent: TestAppComponent): Builder
        fun mainModule(mainModule: MainModule): Builder
        fun build(): TestMainComponent
    }
}
