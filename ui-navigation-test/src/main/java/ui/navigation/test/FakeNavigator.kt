package ui.navigation.test

import ui.navigation.NavigationConfig
import ui.navigation.Navigator

class FakeNavigator : Navigator {

    override fun navigate(config: NavigationConfig): String {
        // no-op
        return "NAVIGATION_NO_OP"
    }
}
