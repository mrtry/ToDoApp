package io.github.mrtry.todolist.misc.ui.navigator

import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import io.github.mrtry.todolist.misc.ui.binding.Bindable

abstract class AbsFragmentNavigator(
    private val activity: AppCompatActivity,
    private val fragment: Fragment
) : AbsNavigator(activity) {
    @Suppress("UNCHECKED_CAST")
    protected fun <T : ViewDataBinding> getFragmentBindingAs(): T =
        (fragment as Bindable<T>).viewBinding

    fun popBackStack() {
        activity.supportFragmentManager.popBackStack()
    }
}