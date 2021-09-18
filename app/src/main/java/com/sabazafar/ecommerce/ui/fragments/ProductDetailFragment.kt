package com.sabazafar.ecommerce.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.sabazafar.ecommerce.R
import com.sabazafar.ecommerce.databinding.AddCartCustomBtnBinding
import com.sabazafar.ecommerce.databinding.ProductDetailFragmentBinding
import com.sabazafar.ecommerce.entity.Product

class ProductDetailFragment : Fragment() {

    companion object {
        fun newInstance() = ProductDetailFragment()
    }

    private lateinit var binding: ProductDetailFragmentBinding
    private lateinit var addCardbinding: AddCartCustomBtnBinding


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = ProductDetailFragmentBinding.inflate(inflater, container, false)
        addCardbinding = AddCartCustomBtnBinding.bind(binding.root)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    private fun initUI() {
        val product: Product
        arguments?.let {
            val safeArgs = ProductDetailFragmentArgs.fromBundle(it)
            product = safeArgs.product

            product?.let {
                Glide.with(requireContext())
                        .load(product.imageUrl)
                        .placeholder(R.color.grey)
                        .into(binding.image)

                binding.name.text = it.name
                binding.description.text = it.description
                binding.price.text = "Rs." + it.price

                binding.addCard.setOnClickListener {
                    binding.addCard.isVisible = false
                    addCardbinding.edit.visibility = View.VISIBLE
                    product.apply {
                        item++
                        addCardbinding.count.text = item.toString()
                    }
                }

                addCardbinding.add.setOnClickListener {
                    product.apply {
                        item++
                        addCardbinding.count.text = item.toString()
                    }
                }
                addCardbinding.delete.setOnClickListener {
                    product.apply {
                        item--
                        addCardbinding.count.text = item.toString()
                        if (item == 0) {
                            binding.addCard.isVisible = true
                        }
                    }
                }
            }
        }
    }

}