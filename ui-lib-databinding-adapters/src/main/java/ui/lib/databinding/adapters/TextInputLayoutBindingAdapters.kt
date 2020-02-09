package ui.lib.databinding.adapters

import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputLayout

@BindingAdapter("errorText")
fun setErrorText(view: TextInputLayout, errorText: String?) {
    view.error = errorText
}