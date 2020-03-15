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
            .build()
    }
}
