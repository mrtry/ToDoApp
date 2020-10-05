package io.github.mrtry.todolist.app.todo.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import io.github.mrtry.todolist.MainApplication
import io.github.mrtry.todolist.R
import io.github.mrtry.todolist.app.todo.ui.navigator.ToDoNavigator
import io.github.mrtry.todolist.app.todo.viewmodel.ToDoViewModel
import io.github.mrtry.todolist.databinding.ActivityToDoBinding
import io.github.mrtry.todolist.di.Injectable
import io.github.mrtry.todolist.di.component.ToDoComponent
import io.github.mrtry.todolist.di.module.ActivityModule
import io.github.mrtry.todolist.misc.ui.binding.Bindable
import javax.inject.Inject

class ToDoActivity : AppCompatActivity(), Injectable<ToDoComponent>, Bindable<ActivityToDoBinding> {
    companion object {
        fun createIntent(context: Context): Intent =
            Intent(context, ToDoActivity::class.java)
    }

    @Inject
    internal lateinit var viewModel: ToDoViewModel

    @Inject
    internal lateinit var navigator: ToDoNavigator

    override val viewBinding: ActivityToDoBinding by lazy {
        DataBindingUtil.setContentView<ActivityToDoBinding>(this, R.layout.activity_to_do)
    }

    override val component: ToDoComponent by lazy {
        MainApplication.getComponent(this)
            .plusToDoComponent(ActivityModule(this))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        component.inject(this)
        viewBinding
    }
}