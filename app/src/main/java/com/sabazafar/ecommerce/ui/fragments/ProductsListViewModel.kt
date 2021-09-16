package com.sabazafar.ecommerce.ui.fragments

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.sabazafar.ecommerce.data.network.ApiService
import com.sabazafar.ecommerce.data.repository.ProductRepository
import com.sabazafar.ecommerce.entity.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class ProductsListViewModel
@Inject
constructor(private val apiService: ApiService) : ViewModel() {

    fun getAllProducts(): Flow<PagingData<Product>> = Pager(
        config = PagingConfig(20, enablePlaceholders = false)
    ) {
        ProductRepository(apiService)
    }.flow.cachedIn(viewModelScope)
}