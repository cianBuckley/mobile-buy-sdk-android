package com.shopify.buy3

import android.widget.FrameLayout
import com.shopify.checkout.ShopifyCheckoutEventListener
import com.shopify.checkout.ShopifyCheckoutManager

class CheckoutPresenter(private val eventListener: ShopifyCheckoutEventListener) {

    fun presentCheckout(url: String, layout: FrameLayout) {
        ShopifyCheckoutManager(eventListener).presentCheckout(url, layout)
    }
}
