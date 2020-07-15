package ui.lib.di

import javax.inject.Scope
import kotlin.annotation.AnnotationRetention.RUNTIME

/** Used to annotate dagger components/sub components which that should live as long as a feature */
@Scope
@Retention(RUNTIME)
annotation class FeatureScope

/** Used to annotate dagger components/sub components which that should live as long as the main feature */
@Scope
@Retention(RUNTIME)
annotation class MainScope

/** Used to annotate dagger components/sub components which that should live as long as a background work */
@Scope
@Retention(RUNTIME)
annotation class BackgroundScope
