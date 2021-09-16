package com.sabazafar.ecommerce.data.network

import com.sabazafar.ecommerce.entity.Product
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    companion object {
        const val BASE_URL = "https://fakestoreapi.com"
    }

    @GET("/products")
    suspend fun getAllProducts(
        @Query("limit") limit : Int
    ) : List<Product>
}