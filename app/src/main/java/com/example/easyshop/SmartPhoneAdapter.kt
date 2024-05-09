package com.example.easyshop

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.easyshop.databinding.SmartPhoneListBinding

class SmartPhoneAdapter(private val smartPhone:List<SmartPhoneList>):RecyclerView.Adapter<SmartPhoneAdapter.SmartPhoneViewHolder>() {
    inner class SmartPhoneViewHolder(private val binding:SmartPhoneListBinding):RecyclerView.ViewHolder(binding.root){
        fun bindData(smartPhoneList: SmartPhoneList){
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
        holder.itemView.setOnClickListener {
            val intent = Intent(context, ProductDetailsAcvtivity::class.java)
            intent.putExtra("Data", data)
            context.startActivity(intent)
        }
    }
}