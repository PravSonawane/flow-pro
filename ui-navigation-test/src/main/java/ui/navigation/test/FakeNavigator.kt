package ui.navigation.test

import ui.navigation.NavigationConfig
import ui.navigation.Navigator

class FakeNavigator : Navigator {

    override fun navigate(config: NavigationConfig) {
        // no-op
    }
}