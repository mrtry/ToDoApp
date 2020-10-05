package io.github.mrtry.todolist.app.splash.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import io.github.mrtry.todolist.MainApplication
import io.github.mrtry.todolist.R
import io.github.mrtry.todolist.app.splash.ui.navigator.SplashNavigator
import io.github.mrtry.todolist.app.splash.ui.result.SplashActivityResult
import io.github.mrtry.todolist.app.splash.viewmodel.SplashViewModel
import io.github.mrtry.todolist.databinding.ActivitySplashBinding
import io.github.mrtry.todolist.di.Injectable
import io.github.mrtry.todolist.di.component.LoginComponent
import io.github.mrtry.todolist.di.module.ActivityModule
import io.github.mrtry.todolist.misc.ui.binding.Bindable
import javax.inject.Inject

class SplashActivity : AppCompatActivity(), Injectable<LoginComponent>, Bindable<ActivitySplashBinding> {

    @Inject
    internal lateinit var viewModel: SplashViewModel

    @Inject
    internal lateinit var navigator: SplashNavigator

    override val viewBinding: ActivitySplashBinding by lazy {
        DataBindingUtil.setContentView<ActivitySplashBinding>(this, R.layout.activity_splash)
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
        SplashActivityResult
            .valueOf(requestCode)
            .getResultHandler(component)
            .handleResult(resultCode, data)
    }
}