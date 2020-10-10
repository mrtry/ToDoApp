package io.github.mrtry.todolist.app.todo.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableList
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import io.github.mrtry.todolist.R
import io.github.mrtry.todolist.app.todo.viewmodel.ToDoListItemViewModel
import io.github.mrtry.todolist.databinding.ListItemToDoBinding
import io.github.mrtry.todolist.misc.extension.addOnListChangedSimpleCallback

class ToDoAdapter(
    context: Context,
    private val items: ObservableList<ToDoListItemViewModel>,
    private val lifecycleOwner: LifecycleOwner
) : RecyclerView.Adapter<ToDoAdapter.ViewHolder>() {
    private val inflater = LayoutInflater.from(context)

    init {
        items.addOnListChangedSimpleCallback {
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(inflater.inflate(R.layout.list_item_to_do, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)

        with(holder.binding) {
            lifecycleOwner = this@ToDoAdapter.lifecycleOwner
            viewModel = item
            executePendingBindings()
        }
    }

    override fun getItemCount(): Int = items.size

    private fun getItem(position: Int): ToDoListItemViewModel = items[position]

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding: ListItemToDoBinding = DataBindingUtil.bind(itemView)!!
    }

}