package com.MatsuNaoPen.household.connection.api.client

import com.MatsuNaoPen.household.connection.api.response.GetVerify

import retrofit2.Call
import retrofit2.http.GET

/**
 * Created by DevUser on 2017/12/21.
 */
interface GetVerifyClient {
    @GET("/v2/home/user/verify")
    fun request(): Call<GetVerify>
}
