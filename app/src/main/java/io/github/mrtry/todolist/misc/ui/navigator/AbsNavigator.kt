package io.github.mrtry.todolist.misc.ui.navigator

import android.app.Activity
import android.content.Intent
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.annotation.StringRes
import androidx.databinding.ViewDataBinding
import com.google.android.material.snackbar.Snackbar
import io.github.mrtry.todolist.misc.ui.binding.Bindable

abstract class AbsNavigator(private val activity: Activity) {

    protected open fun getBinding(): ViewDataBinding =
        (activity as Bindable<ViewDataBinding>).viewBinding

    @Suppress("UNCHECKED_CAST")
    protected fun <T : ViewDataBinding> getBindingAs(): T =
        (activity as Bindable<T>).viewBinding

    fun finishApplication() {
        activity.finishAndRemoveTask()
    }

    fun finishCurrentActivity() {
        activity.finish()
    }

    fun finishCurrentActivityWithResultOk(data: Intent? = null) {
        activity.setResult(Activity.RESULT_OK, data)
        activity.finish()
    }

    fun finishCurrentActivityWithResultCanceled(data: Intent? = null) {
        activity.setResult(Activity.RESULT_CANCELED, data)
        activity.finish()
    }

    open fun showSnackBar(
        @StringRes messageId: Int, length: Int = Snackbar.LENGTH_SHORT
    ) {
        Snackbar.make(getBinding().root, messageId, length)
            .show()
    }

    fun hideKeyboard() {
        val imm: InputMethodManager =
            activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        val view = activity.currentFocus ?: View(activity)
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    fun invalidateOptionsMenu() {
        activity.invalidateOptionsMenu()
    }

    open fun onBackPressed() {
        activity.onBackPressed()
    }
}