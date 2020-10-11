package io.github.mrtry.todolist.misc.ui.binding

import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

@BindingAdapter("onRefresh")
fun SwipeRefreshLayout.setOnRefreshListener(listener: SwipeRefreshLayout.OnRefreshListener) {
    setOnRefreshListener(listener)
}

@BindingAdapter("editorActionListener")
fun EditText.setEditorActionListenerForBinding(listener: TextView.OnEditorActionListener) {
    setOnEditorActionListener(listener)
}

@BindingAdapter("onLongClick")
fun View.setLongClickListener(listener: View.OnLongClickListener) {
    setOnLongClickListener(listener)
}