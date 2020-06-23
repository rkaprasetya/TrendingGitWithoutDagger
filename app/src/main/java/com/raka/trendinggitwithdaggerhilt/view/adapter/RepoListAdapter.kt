package com.raka.myapplication.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.raka.myapplication.data.model.compact.ItemsCompact
import com.raka.trendinggitwithdaggerhilt.view.adapter.viewholders.RepoListViewHolder
import com.raka.trendinggitwithdaggerhilt.databinding.ViewRepoListItemBinding

class RepoListAdapter():RecyclerView.Adapter<RepoListViewHolder>() {
    var repoList:MutableList<ItemsCompact> = mutableListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val dataBinding = ViewRepoListItemBinding.inflate(inflater,parent,false)
        return RepoListViewHolder(dataBinding)
    }

    override fun getItemCount() = repoList.size

    override fun onBindViewHolder(holder: RepoListViewHolder, position: Int) {
        holder.setup(repoList[position])
    }

    fun updateRepoList(repoList:MutableList<ItemsCompact>){
        this.repoList.addAll(repoList)
        notifyDataSetChanged()
    }
}