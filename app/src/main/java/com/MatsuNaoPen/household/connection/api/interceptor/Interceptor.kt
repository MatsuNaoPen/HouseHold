package com.MatsuNaoPen.household.connection.api.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

/**
 * Created by DevUser on 2017/12/18.
 */
class OAuthHeaderInterceptor(token: String) : Interceptor {
    val mToken: String = token
    val consumer_key = "e1691b2f596306eee507a5c66bcf5e7181ebcccc"
    val consumer_secret = "5b92b25d9f24c79a8681c3dac796c3fb16f43f1c"

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()

        request = request.newBuilder()
                .header("Authorization", "Bearer " + mToken)
                .addHeader("consumer_key", consumer_key)
                .addHeader("consumer_secret", consumer_secret)
                .build()

        return chain.proceed(request)
    }
}