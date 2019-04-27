package com.MatsuNaoPen.household.connection.api.service

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import se.akerfeldt.okhttp.signpost.OkHttpOAuthConsumer
import se.akerfeldt.okhttp.signpost.SigningInterceptor

/**
 * Created by DevUser on 2017/12/21.
 */
open class BaseService {
    fun getRetrofit(consumer: OkHttpOAuthConsumer): Retrofit {
        val apiUrl = "https://api.zaim.net/"
        val client = OkHttpClient.Builder()
                .addInterceptor(SigningInterceptor(consumer))
                .build()

        val retrofit = Retrofit.Builder()
                .baseUrl(apiUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        return retrofit
    }
}