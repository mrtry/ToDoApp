package io.github.mrtry.todolist.app.todo.ui.navigator

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import io.github.mrtry.todolist.R
import io.github.mrtry.todolist.databinding.FragmentEditTaskBinding
import io.github.mrtry.todolist.di.scope.FragmentScope
import io.github.mrtry.todolist.misc.ui.navigator.AbsFragmentNavigator
import javax.inject.Inject

@FragmentScope
class EditTaskNavigator
@Inject constructor(
    activity: AppCompatActivity,
    private val fragment: Fragment
) : AbsFragmentNavigator(activity, fragment) {
    fun dismissDialog() {
        (fragment as DialogFragment).dismiss()
    }

    fun showSavingAlert() {
        showAlert(
            R.string.edit_task_fragment_alert_confirm,
            R.string.edit_task_fragment_alert_label_positive,
            { _, _ -> dismissDialog() }
        )
    }

    override fun showSnackBar(messageId: Int, length: Int) {
        Snackbar.make(getFragmentBindingAs<FragmentEditTaskBinding>().container, messageId, length).show()
    }
}