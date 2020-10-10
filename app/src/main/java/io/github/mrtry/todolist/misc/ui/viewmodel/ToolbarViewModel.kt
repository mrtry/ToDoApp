package io.github.mrtry.todolist.misc.ui.viewmodel

import android.app.Activity
import androidx.lifecycle.MutableLiveData
import io.github.mrtry.todolist.di.scope.ActivityScope
import javax.inject.Inject

@ActivityScope
class ToolbarViewModel
@Inject constructor(
    activity: Activity
) {
    val title: MutableLiveData<CharSequence> = MutableLiveData(activity.title)
}