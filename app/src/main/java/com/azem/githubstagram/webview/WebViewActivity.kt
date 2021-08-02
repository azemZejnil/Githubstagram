package com.azem.githubstagram.webview

import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.azem.githubstagram.api.GitApi
import com.azem.githubstagram.databinding.ActivityWebViewBinding


class WebViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityWebViewBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val webUrl = intent.getStringExtra(GitApi.EXTERNAL_URL)
        val webName = intent.getStringExtra(GitApi.EXTERNAL_NAME)

        supportActionBar?.title = webName

        binding.apply {
            webView.webViewClient = MyWebClient()
            webView.settings.setSupportZoom(true)
            webView.settings.supportMultipleWindows()
            if (webUrl != null) {
                webView.loadUrl(webUrl)
            }
        }
    }

    class MyWebClient : WebViewClient() {
        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
            view.loadUrl(url)
            return true
        }
    }
}
