package com.nikhil.androidbottomnavtemplate.screens.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.nikhil.androidbottomnavtemplate.BR
import com.nikhil.androidbottomnavtemplate.common.models.UniItem
import com.nikhil.androidbottomnavtemplate.databinding.ItemUniversityBinding

class UniItemsAdapter : RecyclerView.Adapter<UniItemsAdapter.BindableViewHolder>() {

    var uniItems: List<UniItem> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindableViewHolder  {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemUniversityBinding.inflate(inflater, parent, false)
        return BindableViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return uniItems.size
    }

    override fun onBindViewHolder(holder: BindableViewHolder, position: Int) {
        holder.bind(uniItems[position])
    }

    fun setUniList(unis: List<UniItem>) {
        this.uniItems = unis.toMutableList()
        notifyDataSetChanged()
    }

    class BindableViewHolder(private val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: UniItem) {
            binding.setVariable(BR.item, item)
            item.webPages?.let {
                if (item.webPages.isNotEmpty())
                    binding.setVariable(BR.website, item.webPages[0])
            }
        }
    }
}