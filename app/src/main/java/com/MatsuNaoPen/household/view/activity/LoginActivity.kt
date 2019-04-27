package com.MatsuNaoPen.household.view.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.webkit.JsResult
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import com.MatsuNaoPen.household.R
import com.MatsuNaoPen.household.connection.api.OAuthRequestAsyncTask
import com.MatsuNaoPen.household.connection.api.OAuthUrlAsyncTask
import com.MatsuNaoPen.household.utils.PreferenceUtils
import kotlinx.android.synthetic.main.activity_login.*
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer
import oauth.signpost.commonshttp.CommonsHttpOAuthProvider


/**
 * Created by DevUser on 2017/12/14.
 */
class LoginActivity : AppCompatActivity() {
    val TAG: String = "LOGIN"
    val consumer_key = ""
    val consumer_secret = ""
    val request_token_url = "https://api.zaim.net/v2/auth/request"
    val access_token_url = "https://api.zaim.net/v2/auth/access"
    val authrize_url = "https://auth.zaim.net/users/auth"

    val mConsumer: CommonsHttpOAuthConsumer = CommonsHttpOAuthConsumer(consumer_key, consumer_secret)
    val mProvider: CommonsHttpOAuthProvider = CommonsHttpOAuthProvider(request_token_url, access_token_url, authrize_url)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val webView: WebView = login_web_view
        webView.webViewClient = WebViewClient()
        webView.settings.javaScriptEnabled = true

        webView.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView, url: String) {
                Log.v(this.javaClass.name, "onPageFinished url = " + url)

//                if (url == LOGIN_COMPLETE_URL) {
                // 認証が完了したら、ページ内からOAuth Verifierコードを抜き出してアラートとして表示する
                webView.loadUrl("javascript:window.alert(document.getElementsByTagName(\'code\')[0].innerHTML);")
//                }
            }
        }

        webView.webChromeClient = object : WebChromeClient() {
            override fun onJsAlert(view: WebView, url: String, message: String, result: JsResult): Boolean {
                Log.d(TAG, "url = " + url)
                Log.d(TAG, "message = " + message)

                val requestCallback = object : RequestCallback {
                    override fun onCompleteOAuthRequest(consumer: CommonsHttpOAuthConsumer) {
                        Log.d(TAG, "token :" + consumer.token)
                        Log.d(TAG, "tokenSecret :" + consumer.tokenSecret)

                        PreferenceUtils.setToken(baseContext, consumer.token)
                        PreferenceUtils.setTokenSecret(baseContext, consumer.tokenSecret)
                        finish()
                    }
                }
                val asyncTask = OAuthRequestAsyncTask(message, mConsumer, mProvider, requestCallback)
                asyncTask.execute()

                return true
            }
        }

        // 認証ページのURLを取得する
        val callback: AccessCallback = object : AccessCallback {
            override fun onComplete(url: String) {
                Log.d(TAG, "authUrl = " + url)

                // 取得したURLを表示する
                webView.loadUrl(url)
            }
        }

        val asyncGetUrlTask = OAuthUrlAsyncTask("https://www.google.co.jp/", mConsumer, mProvider, callback)
        asyncGetUrlTask.execute()
    }

    interface RequestCallback {
        fun onCompleteOAuthRequest(authUrl: CommonsHttpOAuthConsumer)
    }

    interface AccessCallback {
        fun onComplete(url: String)
    }
}
