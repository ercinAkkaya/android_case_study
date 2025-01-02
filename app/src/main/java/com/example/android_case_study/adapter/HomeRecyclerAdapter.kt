package com.example.android_case_study.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android_case_study.core.util.extensions.loadImage
import com.example.android_case_study.core.util.extensions.placeHolder
import com.example.android_case_study.databinding.ProductListItemBinding
import com.example.android_case_study.domain.model.Product

class HomeRecyclerAdapter(private val productList: ArrayList<Product>) :
    RecyclerView.Adapter<HomeRecyclerAdapter.ProductViewHolder>() {

    class ProductViewHolder(private val binding: ProductListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(product: Product) {
            binding.productPriceTxt.text = product.price
            binding.productDescriptionTxt.text = product.name
            binding.productImageView.loadImage(product.image, placeHolder(binding.root.context))
            // Bind other product properties as needed
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ProductListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(productList[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateProducts(newProductList: List<Product>) {
        productList.clear()
        productList.addAll(newProductList)
        notifyDataSetChanged()
    }

}