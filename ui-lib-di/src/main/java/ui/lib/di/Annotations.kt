package ui.lib.di

import android.app.Service
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import javax.inject.Scope
import kotlin.annotation.AnnotationRetention.RUNTIME

/** Used to annotate dagger components/sub components which that should live as long as a [Fragment] */
@Scope
@Retention(RUNTIME)
annotation class FeatureScope

/** Used to annotate dagger components/sub components which that should live as long as an [AppCompatActivity] */
@Scope
@Retention(RUNTIME)
annotation class ActivityScope

/** Used to annotate dagger components/sub components which that should live as long as a [Service] */
@Scope
@Retention(RUNTIME)
annotation class ServiceScope
