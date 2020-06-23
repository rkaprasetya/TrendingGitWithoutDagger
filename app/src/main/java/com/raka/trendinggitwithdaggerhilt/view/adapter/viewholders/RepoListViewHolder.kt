package com.raka.trendinggitwithdaggerhilt.view.adapter.viewholders

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.raka.myapplication.data.model.compact.ItemsCompact
import com.raka.trendinggitwithdaggerhilt.BR
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.view_repo_list_item.view.*

class RepoListViewHolder constructor(
    private val dataBinding: ViewDataBinding
) : RecyclerView.ViewHolder(dataBinding.root) {
    private val avatarImage = itemView.item_avatar!!
    fun setup(itemData: ItemsCompact){
       dataBinding.setVariable(BR.itemData,itemData)
        dataBinding.executePendingBindings()
        Picasso.get().load(itemData.avatar_url).into(avatarImage)
//        itemView.onClick {
//            val bundle = bundleOf("url" to itemData.html_url)
//            itemView.findNavController().navigate(R.id.action_repoListFragment_to_repoDetailFragment,bundle)
//        }
    }
}