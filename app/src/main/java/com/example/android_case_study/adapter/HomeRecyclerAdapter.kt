package com.example.android_case_study.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android_case_study.core.util.extensions.loadImage
import com.example.android_case_study.core.util.extensions.placeHolder
import com.example.android_case_study.data.local.entity.CartEntity
import com.example.android_case_study.data.local.entity.FavoriteEntity
import com.example.android_case_study.data.mapper.toDetailModel
import com.example.android_case_study.databinding.ProductListItemBinding
import com.example.android_case_study.domain.model.Product
import com.example.android_case_study.presentation.ui.product_list.ProductListAction

class HomeRecyclerAdapter(
    private val productList: ArrayList<Product>,
    private val onAction: (ProductListAction) -> Unit
) : RecyclerView.Adapter<HomeRecyclerAdapter.ProductViewHolder>() {

    class ProductViewHolder(private val binding: ProductListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(product: Product, onAction: (ProductListAction) -> Unit) {
            binding.productPriceTxt.text = product.price
            binding.productDescriptionTxt.text = product.name
            binding.productImageView.loadImage(product.image, placeHolder(binding.root.context))
            binding.unSelectedStar.setOnClickListener {
                binding.unSelectedStar.visibility = View.GONE
                binding.selectedStar.visibility = View.VISIBLE
                val favoriteEntity = FavoriteEntity(
                    id = product.id,
                    brand = product.brand,
                    createdAt = product.createdAt,
                    description = product.description,
                    image = product.image,
                    model = product.model,
                    name = product.name,
                    price = product.price
                )
                onAction(ProductListAction.AddFavorite(favoriteEntity))
            }

            //TODO delete yapılacak
            binding.selectedStar.setOnClickListener {
                binding.selectedStar.visibility = View.GONE
                binding.unSelectedStar.visibility = View.VISIBLE
                onAction(ProductListAction.DeleteFavorite(product.name))
            }
            itemView.setOnClickListener {
                onAction(ProductListAction.NavigateToDetail(product.toDetailModel()))
            }
            binding.btnAddToCart.setOnClickListener{
                val cartEntity = CartEntity(
                    id = product.id,
                    brand = product.brand,
                    createdAt = product.createdAt,
                    description = product.description,
                    image = product.image,
                    model = product.model,
                    name = product.name,
                    price = product.price
                )
                onAction(ProductListAction.AddToCart(cartEntity))
            }
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
        holder.bind(productList[position], onAction)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateProducts(newProductList: List<Product>) {
        productList.clear()
        productList.addAll(newProductList)
        notifyDataSetChanged()
    }
}