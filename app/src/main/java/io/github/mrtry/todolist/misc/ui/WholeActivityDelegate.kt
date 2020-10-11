package io.github.mrtry.todolist.misc.ui

import android.app.Activity
import android.app.Application
import android.os.Bundle
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WholeActivityDelegate
@Inject constructor(
) : Application.ActivityLifecycleCallbacks {

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        Timber.v(activity.javaClass.simpleName + "#onCreate")
    }

    override fun onActivityStarted(activity: Activity) {
        Timber.v(activity.javaClass.simpleName + "#onStart")
    }

    override fun onActivityResumed(activity: Activity) {
        Timber.v(activity.javaClass.simpleName + "#onResume")
    }

    override fun onActivityPaused(activity: Activity) {
        Timber.v(activity.javaClass.simpleName + "#onPause")
    }

    override fun onActivityStopped(activity: Activity) {
        Timber.v(activity.javaClass.simpleName + "#onStop")
    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
        Timber.v(activity.javaClass.simpleName + "#onSaveInstanceState")
    }

    override fun onActivityDestroyed(activity: Activity) {
        Timber.v(activity.javaClass.simpleName + "#onDestroy")
    }
}