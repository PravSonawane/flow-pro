package ui.lib.views.text

import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import com.google.android.material.textfield.TextInputEditText
import ui.lib.views.types.TextResourceBinding

@BindingAdapter("android:text")
fun setText(view: TextInputEditText, text: MutableLiveData<TextResourceBinding>) {
    text.value?.let {
        it.textRes?.let { resId -> view.setText(resId) }
        it.text?.let { str -> view.setText(str) } // text prioritized over resource
    }
}

@BindingAdapter("android:hint")
fun setHint(view: TextInputEditText, hint: MutableLiveData<TextResourceBinding>) {
    hint.value?.let {
        it.textRes?.let { resId -> view.setHint(resId) }
        it.text?.let { str -> view.hint = str } // text prioritized over resource
    }
}