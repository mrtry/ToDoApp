package io.github.mrtry.todolist.todo.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.github.mrtry.todolist.MainApplication
import io.github.mrtry.todolist.R
import io.github.mrtry.todolist.di.Injectable
import io.github.mrtry.todolist.di.component.LoginComponent
import io.github.mrtry.todolist.di.module.ActivityModule
import io.github.mrtry.todolist.todo.viewmodel.LoginViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancelChildren
import javax.inject.Inject

class LoginActivity : AppCompatActivity(), Injectable<LoginComponent> {

    @Inject
    internal lateinit var viewModel: LoginViewModel

    @Inject
    internal lateinit var coroutineScope: CoroutineScope

    override val component: LoginComponent by lazy {
        MainApplication.getComponent(this)
            .plusLoginComponent(ActivityModule(this))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        component.inject(this)

        setContentView(R.layout.activity_login)
    }

    override fun onStop() {
        super.onStop()
        coroutineScope.coroutineContext.cancelChildren()
    }
}