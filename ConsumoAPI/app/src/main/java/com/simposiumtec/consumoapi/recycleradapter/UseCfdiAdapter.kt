package com.simposiumtec.consumoapi.recycleradapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.simposiumtec.consumoapi.R
import com.simposiumtec.consumoapi.models.UseCfdiAdapterModel

class UseCfdiAdapter(private var adapterModel: List<UseCfdiAdapterModel>, private val listener: OnItemClickListener):
    RecyclerView.Adapter<UseCfdiAdapter.ViewHolder>(){

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val description = itemView.findViewById<TextView>(R.id.txtTitle)
        val code = itemView.findViewById<TextView>(R.id.txtCode)
        val physicalPerson = itemView.findViewById<ImageView>(R.id.imgPhysicalPerson)
        val moralPerson = itemView.findViewById<ImageView>(R.id.imgMoralPerson)

        init {

            itemView.setOnClickListener { v: View ->
                v.setOnClickListener(this)
            }
        }

        override fun onClick(p0: View?) {
            val position = bindingAdapterPosition
        }
    }

    interface OnItemClickListener {
        fun onItemClick(v: View, position: Int, data: UseCfdiAdapterModel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_usecfdi_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = adapterModel[position]

        holder.description.text = data.Description
        holder.code.text = data.CodeUseCfdi

        if (data.AppliesPhysicalPerson == "true") {
            holder.physicalPerson.setImageResource(com.simposiumtec.consumoapi.R.drawable.outline_check_24)
            holder.physicalPerson.setColorFilter(holder.physicalPerson.context.getColor(com.simposiumtec.consumoapi.R.color.bgSuccess))
        } else {
            holder.physicalPerson.setImageResource(com.simposiumtec.consumoapi.R.drawable.outline_close_24)
            holder.physicalPerson.setColorFilter(holder.physicalPerson.context.getColor(com.simposiumtec.consumoapi.R.color.red))
        }

        if (data.AplliesMoralPerson == "true") {
            holder.moralPerson.setImageResource(com.simposiumtec.consumoapi.R.drawable.outline_check_24)
            holder.moralPerson.setColorFilter(holder.physicalPerson.context.getColor(com.simposiumtec.consumoapi.R.color.bgSuccess))
        } else {
            holder.moralPerson.setImageResource(com.simposiumtec.consumoapi.R.drawable.outline_close_24)
            holder.moralPerson.setColorFilter(holder.physicalPerson.context.getColor(com.simposiumtec.consumoapi.R.color.red))
        }
    }

    override fun getItemCount(): Int {

        return adapterModel.size
    }
}