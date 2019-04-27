package com.MatsuNaoPen.household.connection.api.client

import com.MatsuNaoPen.household.connection.api.response.GetMoney
import retrofit2.Call
import retrofit2.http.GET

/**
 * Created by DevUser on 2017/12/17.
 */
interface GetMoneyClient {
    @GET("/v2/home/money")
    fun request(): Call<GetMoney>
}