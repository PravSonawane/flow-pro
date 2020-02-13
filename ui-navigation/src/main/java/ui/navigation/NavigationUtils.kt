package ui.navigation

import android.net.Uri
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController

fun navigate(fragment: Fragment, deepLink: Uri, navOptions: NavOptions? = null) {
    fragment.findNavController().navigate(deepLink, navOptions)
}
