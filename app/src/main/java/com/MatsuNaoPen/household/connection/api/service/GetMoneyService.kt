package com.MatsuNaoPen.household.connection.api.service

import com.MatsuNaoPen.household.connection.api.client.GetMoneyClient
import se.akerfeldt.okhttp.signpost.OkHttpOAuthConsumer

/**
 * Created by DevUser on 2017/12/17.
 */
class GetMoneyService {
    companion object {
        fun createService(consumer: OkHttpOAuthConsumer): GetMoneyClient {
            return BaseService().getRetrofit(consumer).create(GetMoneyClient::class.java)
        }
    }
}