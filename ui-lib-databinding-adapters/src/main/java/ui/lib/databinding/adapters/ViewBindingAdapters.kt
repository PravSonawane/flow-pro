package ui.lib.databinding.adapters

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("android:visibility")
fun setVisibility(view: View, isVisible: Boolean?) {
    isVisible?.let { if (it) view.visibility = View.VISIBLE else view.visibility = View.GONE }
}
