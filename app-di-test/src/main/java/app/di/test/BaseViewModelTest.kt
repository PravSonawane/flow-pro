package app.di.test

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.github.plastix.rxschedulerrule.RxSchedulerRule
import org.junit.Rule
import org.junit.rules.TestRule

abstract class BaseViewModelTest : BaseTest() {

    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val schedulerRule = RxSchedulerRule()
}
