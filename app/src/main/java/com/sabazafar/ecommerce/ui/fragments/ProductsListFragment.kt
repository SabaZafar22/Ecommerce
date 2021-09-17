package com.sabazafar.ecommerce.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.facebook.shimmer.ShimmerFrameLayout
import com.sabazafar.ecommerce.adapter.ProductAdapter
import com.sabazafar.ecommerce.databinding.ProductsListFragmentBinding
import com.sabazafar.ecommerce.entity.Product
import com.sabazafar.ecommerce.listener.ProductClickedListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class ProductsListFragment : Fragment() , ProductClickedListener {

    companion object {
        fun newInstance() = ProductsListFragment()
    }

    private val viewModel: ProductsListViewModel by viewModels()
    private lateinit var binding: ProductsListFragmentBinding
    private lateinit var productsAdapter : ProductAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ProductsListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
        initUI()

        lifecycleScope.launchWhenStarted {
            viewModel.getAllProducts().collectLatest { response->
                binding.layoutContainer.loaderContainer.isVisible = false
               // binding.recyclerview.isVisible =  true
                productsAdapter.submitData(response)
            }
        }
    }

    private fun initUI() {
        val shimmerFrameLayout: ShimmerFrameLayout = binding.layoutContainer.loaderContainer
        shimmerFrameLayout.startShimmer()
        productsAdapter = ProductAdapter(requireContext(),this)
        binding.apply {
            recyclerview.apply {
                setHasFixedSize(true)
                layoutManager = GridLayoutManager(context, 2)
                adapter = productsAdapter
            }
        }
    }

    private val callback: OnBackPressedCallback =
        object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                requireActivity().finish()
            }
        }

    override fun onProductClick(product: Product) {
        val action = ProductsListFragmentDirections.actionProductsListToProductDetailFragment(product)
        view?.let { Navigation.findNavController(it).navigate(action) }
    }

}