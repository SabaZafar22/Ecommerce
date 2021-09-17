package com.sabazafar.ecommerce.listener

import com.sabazafar.ecommerce.entity.Product

interface ProductClickedListener {
    fun onProductClick(product : Product)
}