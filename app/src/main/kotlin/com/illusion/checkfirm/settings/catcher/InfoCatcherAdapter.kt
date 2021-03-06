package com.illusion.checkfirm.settings.catcher

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageButton
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textview.MaterialTextView
import com.illusion.checkfirm.R
import com.illusion.checkfirm.database.catcher.InfoCatcherEntity
import com.illusion.checkfirm.databinding.RowSearchItemsBinding

class InfoCatcherAdapter(private val context: Context, private var deviceList: List<InfoCatcherEntity>,
                         val onClickListener: MyAdapterListener) : RecyclerView.Adapter<InfoCatcherAdapter.MyViewHolder>() {

    inner class MyViewHolder(binding: RowSearchItemsBinding) : RecyclerView.ViewHolder(binding.root) {
        private val delete: AppCompatImageButton = binding.delete
        var device: MaterialTextView = binding.device

        init {
            delete.setOnClickListener { onClickListener.onDeleteClicked(deviceList[bindingAdapterPosition].device) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = RowSearchItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = deviceList[position]
        holder.device.text = String.format(context.getString(R.string.device_format), data.model, data.csc)
    }

    override fun getItemCount(): Int {
        return deviceList.size
    }

    internal fun setDevices(deviceList: List<InfoCatcherEntity>) {
        this.deviceList = deviceList
        notifyDataSetChanged()
    }

    interface MyAdapterListener {
        fun onDeleteClicked(device: String)
    }
}
