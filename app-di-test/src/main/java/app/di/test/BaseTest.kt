package app.di.test

abstract class BaseTest {

    fun appComponent(): TestAppComponent {
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
