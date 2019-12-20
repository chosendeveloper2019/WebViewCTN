package com.kritsanadum.webviewctn

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.*
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var context = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_go.setOnClickListener {

            var getURL = "http://"+et_url.text.toString()
//            Toast.makeText(context,"${getURL}",Toast.LENGTH_LONG).show()
            requestURL(getURL)
        }
        supportActionBar?.hide()
    }

    fun requestURL(url : String){

        webView.webViewClient = object : WebViewClient()
        {
            override fun onReceivedError(
                view: WebView?,
                request: WebResourceRequest?,
                error: WebResourceError?
            ) {
                super.onReceivedError(view, request, error)
                Toast.makeText(context,"Received Error!!!",Toast.LENGTH_LONG).show()
            }

            override fun onReceivedHttpError(
                view: WebView?,
                request: WebResourceRequest?,
                errorResponse: WebResourceResponse?
            ) {
                super.onReceivedHttpError(view, request, errorResponse)
                Toast.makeText(context,"Received HTTP Error!!!",Toast.LENGTH_LONG).show()

                var getURL = "https://"+et_url.text.toString()
//            Toast.makeText(context,"${getURL}",Toast.LENGTH_LONG).show()
                requestURL(getURL)
            }

        }
        webView.settings.javaScriptEnabled = true
        webView.settings.allowContentAccess = true
        webView.settings.allowFileAccess = true
        webView.loadUrl(url)


    }

    override fun onBackPressed() {
        if(webView.canGoBack()){
            webView.goBack()
        }else{
            super.onBackPressed()
        }


    }



}
