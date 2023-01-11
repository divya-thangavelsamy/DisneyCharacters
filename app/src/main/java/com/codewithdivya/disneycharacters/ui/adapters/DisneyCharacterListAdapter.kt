package com.codewithdivya.disneycharacters.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.codewithdivya.disneycharacters.business.domainModel.DisneyCharacterListDomainModel
import com.codewithdivya.disneycharacters.databinding.DisneyCharacterItemBinding
import kotlin.properties.Delegates

class DisneyCharacterListAdapter : RecyclerView.Adapter<DisneyCharacterListAdapter.ViewHolder>() {

    lateinit var dataSet: List<DisneyCharacterListDomainModel>

    class ViewHolder(val binding: DisneyCharacterItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: DisneyCharacterListDomainModel) {
            binding.a.text = item.name
            binding.image.load(item.imageUrl)
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = DisneyCharacterItemBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = dataSet[position]
        holder.bind(movie)
    }

    override fun getItemCount() = dataSet.size
}


