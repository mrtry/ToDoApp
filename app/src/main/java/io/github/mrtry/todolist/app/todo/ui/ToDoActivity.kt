package io.github.mrtry.todolist.app.todo.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import io.github.mrtry.todolist.MainApplication
import io.github.mrtry.todolist.R
import io.github.mrtry.todolist.app.todo.ui.adapter.ToDoAdapter
import io.github.mrtry.todolist.app.todo.ui.menu.ToDoMenuAction
import io.github.mrtry.todolist.app.todo.ui.navigator.ToDoNavigator
import io.github.mrtry.todolist.app.todo.viewmodel.ToDoViewModel
import io.github.mrtry.todolist.databinding.ActivityToDoBinding
import io.github.mrtry.todolist.di.Injectable
import io.github.mrtry.todolist.di.component.ToDoComponent
import io.github.mrtry.todolist.di.module.ActivityModule
import io.github.mrtry.todolist.misc.ui.binding.Bindable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancelChildren
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

    @Inject
    internal lateinit var coroutineScope: CoroutineScope

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
        setSupportActionBar(viewBinding.toolbar)

        with(viewBinding) {
            viewModel = this@ToDoActivity.viewModel
            lifecycleOwner = this@ToDoActivity

            with(bannerAddTask) {
                viewModel = this@ToDoActivity.viewModel.taskViewModel
                lifecycleOwner = this@ToDoActivity
            }

            with(list) {
                adapter = ToDoAdapter(this@ToDoActivity, this@ToDoActivity.viewModel.items, this@ToDoActivity)
                layoutManager = LinearLayoutManager(this@ToDoActivity)
            }
        }

        viewModel.load()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_activity_to_do, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = ToDoMenuAction
        .valueOf(item.itemId)
        .getActionHandler(component)
        .handleAction() || super.onOptionsItemSelected(item)

    override fun onStop() {
        super.onStop()
        coroutineScope.coroutineContext.cancelChildren()
    }
}