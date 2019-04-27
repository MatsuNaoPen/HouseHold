package com.MatsuNaoPen.household.connection.api.service

import com.MatsuNaoPen.household.connection.api.client.GetVerifyClient
import se.akerfeldt.okhttp.signpost.OkHttpOAuthConsumer

/**
 * Created by DevUser on 2017/12/17.
 */
class GetVerifyService {
    companion object {
        fun createService(consumer: OkHttpOAuthConsumer): GetVerifyClient {
            return BaseService().getRetrofit(consumer).create(GetVerifyClient::class.java)
        }
    }
}