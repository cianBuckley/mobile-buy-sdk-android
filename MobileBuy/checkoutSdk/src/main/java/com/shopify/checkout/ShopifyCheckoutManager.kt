package com.shopify.checkout

import android.graphics.Color
import android.widget.FrameLayout
import com.shopify.checkout.models.CheckoutOptions
import com.shopify.checkout.webMessage.MessageParserListener
import com.shopify.checkout.webView.CheckoutWebViewController

open class ShopifyCheckoutManager(
    private val eventListeners: ShopifyCheckoutEventListener,
    var webViewController: CheckoutWebViewController = CheckoutWebViewController()
    ) {
    private val shopifyCheckoutWebBridgeListener = MessageParserListener(eventListeners)

    fun presentCheckout(url: String, layout: FrameLayout) {
        webViewController.defaults = null
        loadCheckout(url, layout)
    }

    fun presentCheckout(url: String, layout: FrameLayout, options: CheckoutOptions) {
        webViewController.defaults = options.defaults
        loadCheckout(url, layout)
    }

    private fun loadCheckout(url: String, layout: FrameLayout) {
        val webView = AndroidWebView(layout.context, listener = shopifyCheckoutWebBridgeListener)
        layout.addView(webView)
        webView.requestDisallowInterceptTouchEvent(true)
        webView.setBackgroundColor(Color.TRANSPARENT)

        webViewController.webView = webView
        webViewController.webViewClientEventListener = AndroidWebViewEventListener(eventListeners)
        webViewController.loadCheckout(url)
    }

    fun completeCheckout() {
        webViewController.completeCheckout()
    }

    fun completeCheckout(oneTimeVaultedToken: String) {
        webViewController.completeCheckout(oneTimeVaultedToken)
    }

    fun version(): String {
        return BuildConfig.SDK_VERSION
    }
}

