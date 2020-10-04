package io.github.mrtry.todolists.todo.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.github.mrtry.todolists.MainApplication
import io.github.mrtry.todolists.R
import io.github.mrtry.todolists.di.Injectable
import io.github.mrtry.todolists.di.component.LoginComponent
import io.github.mrtry.todolists.di.module.ActivityModule
import io.github.mrtry.todolists.todo.viewmodel.LoginViewModel
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