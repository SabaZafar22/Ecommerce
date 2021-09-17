package com.sabazafar.ecommerce.ui.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.sabazafar.ecommerce.R
import com.sabazafar.ecommerce.databinding.ProductDetailFragmentBinding
import com.sabazafar.ecommerce.entity.Product
import okhttp3.internal.threadName

class ProductDetailFragment : Fragment() {

    companion object {
        fun newInstance() = ProductDetailFragment()
    }

    private lateinit var viewModel: ProductDetailViewModel
    private lateinit var binding: ProductDetailFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ProductDetailFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    private fun initUI() {
        val product : Product
        arguments?.let {
            val safeArgs = ProductDetailFragmentArgs.fromBundle(it)
            product = safeArgs.product

            product?.apply {
                Glide.with(requireContext())
                        .load(product.imageUrl)
                        .transform(RoundedCorners(20))
                        .placeholder(R.color.grey )
                        .into(binding.image)

                binding.name.text = name
                binding.description.text = description
                binding.price.text = "Rs." + price


            }

        }
    }

}