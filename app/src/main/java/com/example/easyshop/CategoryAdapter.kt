package com.example.easyshop

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.easyshop.databinding.CategoryListBinding

class CategoryAdapter(private val category:List<CategoryList>) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {
    inner class CategoryViewHolder(private val binding: CategoryListBinding): RecyclerView.ViewHolder(binding.root){
        fun bindData(categoryList: CategoryList){
            with(binding){
                categoryIcon.setImageResource(categoryList.albumIV)
                categoryTitle.text=categoryList.categoryTitle
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = CategoryListBinding.inflate(layoutInflater, parent, false)
        return CategoryViewHolder(binding)
    }

    override fun getItemCount() = category.size

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val data = category[position]
        holder.bindData(data)
        val context = holder.itemView.context
        holder.itemView.setOnClickListener {
            val intent = Intent(context, SmartPhonesActivity::class.java)
            intent.putExtra("Data", data)
            context.startActivity(intent)
        }
    }
}