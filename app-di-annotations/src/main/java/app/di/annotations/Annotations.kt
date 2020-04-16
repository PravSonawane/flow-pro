package app.di.annotations

/** Used to mark a Application context. */
@Retention(AnnotationRetention.RUNTIME)
annotation class ApplicationContext

/** Used to mark an Activity context. */
@Retention(AnnotationRetention.RUNTIME)
annotation class ActivityContext

/** Used to mark an [Int] resource ID for the navigation host. */
@Retention(AnnotationRetention.RUNTIME)
annotation class NavHostResourceId
