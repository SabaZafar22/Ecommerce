package com.sabazafar.ecommerce.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.sabazafar.ecommerce.data.network.ApiService
import com.sabazafar.ecommerce.entity.Product
import java.lang.Exception

class ProductRepository constructor(private val apiService: ApiService) :
    PagingSource<Int, Product>() {

    override fun getRefreshKey(state: PagingState<Int, Product>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Product> {
        val page = params.key ?: 1

        return try {
            val response = apiService.getAllProducts(params.loadSize)
            LoadResult.Page(
                response,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (response.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}