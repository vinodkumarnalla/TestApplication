package com.android.app.testapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.android.app.testapplication.databinding.ChildItemBinding
import com.android.app.testapplication.network.Response
import com.bumptech.glide.Glide

class ItemsListAdapter(private val list: List<Response>) :
    RecyclerView.Adapter<ItemsListAdapter.ViewHolder>() {

    private val onClickLiveData = MutableLiveData<Response>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ChildItemBinding.inflate(LayoutInflater.from(parent.context))
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun getOnClickLiveData(): LiveData<Response> {
        return onClickLiveData
    }

    inner class ViewHolder(private val binding: ChildItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(item: Response) {
            binding.txtDesc.text = item.description
            binding.txtTitle.text = item.name
            Glide.with(binding.root.context).load(item.image).into(binding.ivItem)
            binding.root.setOnClickListener {
                onClickLiveData.postValue(item)
            }
        }
    }
}