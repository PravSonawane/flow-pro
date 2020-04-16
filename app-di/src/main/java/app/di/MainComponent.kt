package app.di

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import app.di.annotations.ActivityContext
import app.di.annotations.NavHostResourceId
import dagger.BindsInstance
import dagger.Component
import ui.lib.base.BaseActivity
import ui.lib.di.ActivityScope
import ui.lib.di.BaseMainComponent
import ui.lib.di.UiModule
import ui.navigation.di.NavigationComponent

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
interface MainComponent : BaseMainComponent, AppComponent, NavigationComponent {
    @ActivityContext fun activity(): Activity
    @NavHostResourceId fun navHostResId(): Int
    fun injectIn(activity: BaseActivity)

    /** Dagger Builder for [MainComponent] */
    @Component.Builder
    interface Builder {
        fun appComponent(appComponent: AppComponent): Builder
        fun mainModule(mainModule: MainModule): Builder
        @BindsInstance
        fun activity(@ActivityContext activity: Activity): Builder
        @BindsInstance
        fun navHostResId(@NavHostResourceId navHostResId: Int): Builder
        fun build(): MainComponent
    }
}
