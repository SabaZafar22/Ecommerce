package com.sabazafar.ecommerce.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.sabazafar.ecommerce.R
import com.sabazafar.ecommerce.databinding.ProductItemBinding
import com.sabazafar.ecommerce.entity.Product
import com.sabazafar.ecommerce.listener.ProductClickedListener


class ProductAdapter(private var context: Context, private var productClickedListener: ProductClickedListener) : PagingDataAdapter<Product, ProductAdapter.ProductsViewHolder>(Diff){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding : ProductItemBinding = ProductItemBinding.inflate(
            layoutInflater,
            parent,
            false)
        return ProductsViewHolder(binding)
    }
    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {
        val product = getItem(position)
        product?.let {
            holder.bind(product, context, productClickedListener)
        }
    }

    class ProductsViewHolder(private val binding : ProductItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(product: Product, context: Context, productClickedListener: ProductClickedListener) {
            binding.apply {
                name.text = product.name
                price.text = "Rs." + product.price
                Glide.with(context)
                    .load(product.imageUrl)
                    .transform(RoundedCorners(10))
                    .placeholder(R.color.grey)
                    .into(image)
            }

            binding.product.setOnClickListener {
                productClickedListener.onProductClick(product)
            }
        }
    }

    object Diff : DiffUtil.ItemCallback<Product>(){
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem == newItem
        }

    }
}