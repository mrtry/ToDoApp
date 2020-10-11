package io.github.mrtry.todolist.app.todo.ui

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import io.github.mrtry.todolist.R
import io.github.mrtry.todolist.app.todo.ui.navigator.EditTaskNavigator
import io.github.mrtry.todolist.app.todo.viewmodel.EditTaskViewModel
import io.github.mrtry.todolist.databinding.FragmentEditTaskBinding
import io.github.mrtry.todolist.di.Injectable
import io.github.mrtry.todolist.di.component.EditTaskComponent
import io.github.mrtry.todolist.di.component.ToDoComponent
import io.github.mrtry.todolist.di.module.FragmentModule
import io.github.mrtry.todolist.di.scope.FragmentScope
import io.github.mrtry.todolist.di.utils.ComponentUtils
import io.github.mrtry.todolist.misc.ui.binding.Bindable
import io.github.mrtry.todolist.task.entity.Task
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancelChildren
import javax.inject.Inject


private const val KEY_TASK = "KEY_TASK"

class EditTaskDialogFragment : DialogFragment(), Injectable<EditTaskComponent>, Bindable<FragmentEditTaskBinding> {
    companion object {
        val TAG: String = EditTaskDialogFragment::class.java.simpleName

        fun newInstance(task: Task): EditTaskDialogFragment {
            val fragment = EditTaskDialogFragment()
            val args = Bundle()
            args.putParcelable(KEY_TASK, task)
            fragment.arguments = args
            return fragment
        }
    }

    override val viewBinding: FragmentEditTaskBinding by lazy {
        FragmentEditTaskBinding.inflate(layoutInflater)
    }

    override lateinit var component: EditTaskComponent

    @Inject
    lateinit var viewModel: EditTaskViewModel

    @Inject
    lateinit var navigator: EditTaskNavigator

    @Inject
    lateinit var coroutineScope: CoroutineScope

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.Widget_FullScreenDialog)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        super.onCreateView(inflater, container, savedInstanceState)
        return viewBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        component = ComponentUtils.getComponent<ToDoComponent>(requireActivity())
            .plusEditTaskComponent(FragmentModule(this))
        component.inject(this)

        with(viewBinding) {
            viewModel = this@EditTaskDialogFragment.viewModel
            lifecycleOwner = this@EditTaskDialogFragment
        }

        requireActivity().onBackPressedDispatcher.addCallback(this) {
            navigator.showSavingAlert()
        }

        // DialogFragmentのcancel()を無効にして、back pressをハンドリングする
        // see: https://stackoverflow.com/a/7622065
        isCancelable = false
        dialog?.setOnKeyListener { _, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_BACK && event.action === KeyEvent.ACTION_UP) {
                navigator.onBackPressed()
                true
            } else false
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(viewBinding.toolbar) {
            setNavigationOnClickListener {
                navigator.onBackPressed()
            }

            inflateMenu(R.menu.menu_fragment_edit_task)
            setOnMenuItemClickListener {
                this@EditTaskDialogFragment.viewModel.onSaveClick()
                true
            }
        }
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
    }

    override fun onStop() {
        super.onStop()
        coroutineScope.coroutineContext.cancelChildren()
    }
}

@FragmentScope
class EditTaskDialogFragmentValueHolder
@Inject constructor(fragment: Fragment) {
    val task: Task = fragment.requireArguments().getParcelable(KEY_TASK)!!
}