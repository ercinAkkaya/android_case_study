package com.example.android_case_study.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android_case_study.databinding.CartListItemBinding
import com.example.android_case_study.domain.model.CartItem

class CartRecyclerAdapter(
    private val cartList: ArrayList<CartItem>,
) : RecyclerView.Adapter<CartRecyclerAdapter.CartViewHolder>() {

    class CartViewHolder(private val binding: CartListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(cartItem: CartItem) {
            binding.productName.text = cartItem.name
            binding.productPrice.text = cartItem.price
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val binding = CartListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CartViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return cartList.size
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        holder.bind(cartList[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateCartItems(newCartList: List<CartItem>) {
        cartList.clear()
        cartList.addAll(newCartList)
        notifyDataSetChanged()
    }
}