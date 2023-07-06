package com.example.todolistcoroutines.views.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todolistcoroutines.databinding.CardToDoItemBinding
import com.example.todolistcoroutines.model.ToDo

class ToDoAdapter(val removeItem: (todo: ToDo) -> Unit) :
    RecyclerView.Adapter<ToDoAdapter.ToDoItemViewHolder>() {

    private var data: List<ToDo> = MutableList<ToDo>(0) { ToDo() }

    inner class ToDoItemViewHolder(val binding: CardToDoItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoItemViewHolder {
        val binding = CardToDoItemBinding.inflate(LayoutInflater.from(parent.context))
        return ToDoItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ToDoItemViewHolder, position: Int) {
        with(holder) {
            with(data[position]) {
                binding.tvItemTitle.text = "$id: $title"
                binding.tvItemDescription.text = description
                binding.buttonDone.setOnClickListener {
                    removeItem(data[position])

                }
            }
        }
    }

    override fun getItemCount(): Int = data.size

    fun setItems(toDoList: List<ToDo>) {
        data = toDoList
        notifyDataSetChanged()
    }


}
