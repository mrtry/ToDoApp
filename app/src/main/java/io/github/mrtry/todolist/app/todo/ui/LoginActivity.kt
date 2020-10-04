package io.github.mrtry.todolist.app.todo.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import io.github.mrtry.todolist.MainApplication
import io.github.mrtry.todolist.R
import io.github.mrtry.todolist.app.todo.ui.navigator.LoginNavigator
import io.github.mrtry.todolist.app.todo.ui.result.LoginActivityResult
import io.github.mrtry.todolist.app.todo.viewmodel.LoginViewModel
import io.github.mrtry.todolist.databinding.ActivityLoginBinding
import io.github.mrtry.todolist.di.Injectable
import io.github.mrtry.todolist.di.component.LoginComponent
import io.github.mrtry.todolist.di.module.ActivityModule
import io.github.mrtry.todolist.misc.ui.binding.Bindable
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject

class LoginActivity : AppCompatActivity(), Injectable<LoginComponent>, Bindable<ActivityLoginBinding> {

    @Inject
    internal lateinit var viewModel: LoginViewModel

    @Inject
    internal lateinit var navigator: LoginNavigator

    @Inject
    internal lateinit var coroutineScope: CoroutineScope

    override val viewBinding: ActivityLoginBinding by lazy {
        DataBindingUtil.setContentView(this, R.layout.activity_login)
    }

    override val component: LoginComponent by lazy {
        MainApplication.getComponent(this)
            .plusLoginComponent(ActivityModule(this))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        component.inject(this)
        viewBinding
    }

    override fun onResume() {
        super.onResume()
        viewModel.ensuredLoggedIn()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        LoginActivityResult
            .valueOf(requestCode)
            .getResultHandler(component)
            .handleResult(resultCode, data)
    }
}