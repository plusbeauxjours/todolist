package com.example.todolist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.databinding.ItemTodoBinding
import com.example.todolist.db.TodoEntity

class TodoRecyclerViewAdapter (private val todoList: ArrayList<TodoEntity>, private val listener: OnItemLongClickListener): RecyclerView.Adapter<TodoRecyclerViewAdapter.MyViewHolder>(){
    inner class MyViewHolder(binding : ItemTodoBinding): RecyclerView.ViewHolder(binding.root){
        val tv_importance = binding.tvImportance
        val tv_title = binding.tvTitle

        val root = binding.root
    }

//    parent는 recyclerView가 위치한 곳의 부모 layout (ConstraintLayout)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        println(parent.context)
        val binding: ItemTodoBinding = ItemTodoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return todoList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val todoData = todoList[position]

        when (todoData.importance){
            1 -> {
                holder.tv_importance.setBackgroundResource(R.color.red)
            }
            2 -> {
                holder.tv_importance.setBackgroundResource(R.color.yellow)
            }
            3 -> {
                holder.tv_importance.setBackgroundResource(R.color.green)
            }
        }
        holder.tv_importance.text = todoData.importance.toString()
        holder.tv_title.text = todoData.title.toString()

        holder.root.setOnLongClickListener{
            listener.onLongClick(position)
//            false는 다른 click event도 실행, true는 오직 long click event만 실행
            false
        }
    }
}