package app.di.test

open class BaseTest {

    private fun appComponent(): TestAppComponent {
        val application = TestApplication()
        return DaggerTestAppComponent.builder()
            .application(application)
            .applicationContext(application)
            .build()
    }

    fun mainComponent(): TestMainComponent {
        return DaggerTestMainComponent.builder()
            .appComponent(appComponent())
            .activity(FakeActivity())
            .navHostResId(-1) // since tests don't use navigation
            .build()
    }
}
