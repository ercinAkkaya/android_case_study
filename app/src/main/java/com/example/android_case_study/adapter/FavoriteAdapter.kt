package com.example.android_case_study.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android_case_study.core.util.extensions.loadImage
import com.example.android_case_study.core.util.extensions.placeHolder
import com.example.android_case_study.databinding.FavoriteListItemBinding
import com.example.android_case_study.domain.model.FavoriteItem
import com.example.android_case_study.presentation.ui.cart.CartFragmentAction
import com.example.android_case_study.presentation.ui.favorite.FavoriteAction

class FavoriteAdapter(
    private val favoriteList: ArrayList<FavoriteItem>,
    private val onAction: (FavoriteAction) -> Unit
) : RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {

    class FavoriteViewHolder(private val binding: FavoriteListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(favoriteItem: FavoriteItem, onAction: (FavoriteAction) -> Unit) {
            binding.favoriteImageView.loadImage(favoriteItem.image, placeHolder(binding.root.context))
            binding.favoriteNameTextView.text = favoriteItem.name
            binding.favoritePriceTextView.text = favoriteItem.price

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val binding = FavoriteListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoriteViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return favoriteList.size
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bind(favoriteList[position], onAction)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateFavoriteItems(newFavoriteList: List<FavoriteItem>) {
        favoriteList.clear()
        favoriteList.addAll(newFavoriteList)
        notifyDataSetChanged()
    }
}