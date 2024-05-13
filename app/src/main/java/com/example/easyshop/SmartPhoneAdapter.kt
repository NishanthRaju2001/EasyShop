package com.example.easyshop

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.easyshop.databinding.SmartPhoneListBinding
import com.example.easyshop.locals.SmartPhoneEntity

class SmartPhoneAdapter(private val smartPhone:List<SmartPhoneEntity>):RecyclerView.Adapter<SmartPhoneAdapter.SmartPhoneViewHolder>() {
    interface OnItemClickListener {
        fun onItemClick(smartPhone: SmartPhoneEntity)
    }
    var itemClickListener: OnItemClickListener? = null
    inner class SmartPhoneViewHolder(private val binding:SmartPhoneListBinding):RecyclerView.ViewHolder(binding.root){
        init {
            binding.root.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    itemClickListener?.onItemClick(smartPhone[position])
                }
            }
        }
        fun bindData(smartPhoneList: SmartPhoneEntity){
            with(binding){
                phoneIV.setImageResource(smartPhoneList.smartPhoneIV)
                phoneTitle.text=smartPhoneList.smartPhoneTitle
                phoneDescription.text=smartPhoneList.smartPhoneDescription
                phoneCost.text=smartPhoneList.price.toString()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SmartPhoneViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = SmartPhoneListBinding.inflate(layoutInflater, parent, false)
        return SmartPhoneViewHolder(binding)
    }

    override fun getItemCount() = smartPhone.size

    override fun onBindViewHolder(holder: SmartPhoneViewHolder, position: Int) {
        val data = smartPhone[position]
        holder.bindData(data)
        val context = holder.itemView.context

    }
}