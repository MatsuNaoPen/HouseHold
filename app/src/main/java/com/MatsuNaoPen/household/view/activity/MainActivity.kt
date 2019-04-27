package com.MatsuNaoPen.household.view.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.MatsuNaoPen.household.R
import com.MatsuNaoPen.household.connection.api.service.GetMoneyService
import com.MatsuNaoPen.household.connection.api.response.GetMoney
import com.MatsuNaoPen.household.connection.api.response.GetVerify
import com.MatsuNaoPen.household.connection.api.service.GetVerifyService
import com.MatsuNaoPen.household.utils.PreferenceUtils
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import se.akerfeldt.okhttp.signpost.OkHttpOAuthConsumer

class MainActivity : AppCompatActivity() {
    val TAG = this.javaClass.name
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
        var loginTranButton: Button = findViewById(R.id.button_go_to_login)
        loginTranButton.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        button_load_money.setOnClickListener {
            val client = GetMoneyService.createService(getConsumer())
            val call = client.request()

            call.enqueue(object : Callback<GetMoney> {
                override fun onResponse(call: Call<GetMoney>?, response: Response<GetMoney>?) {
                    when (response!!.code()) {
                        400, 401, 404 ->
                            Log.d(TAG, "on Response:" + response.errorBody()!!.string())
                        else -> {
                            Log.d(TAG, "on Response:" + response.toString())
                            val result: GetMoney = response.body()!!
                            activity_main_show_area_text.text = result.getShowDataSample()
                        }
                    }
                }

                override fun onFailure(call: Call<GetMoney>?, t: Throwable?) {
                    Log.d(TAG, "on onFailure:" + t.toString())
                }
            })
        }

        button_load_verify.setOnClickListener {
            val client = GetVerifyService.createService(getConsumer())
            val call = client.request()

            call.enqueue(object : Callback<GetVerify> {
                override fun onResponse(call: Call<GetVerify>?, response: Response<GetVerify>?) {
                    when (response!!.code()) {
                        400, 401, 404 ->
                            Log.d(TAG, "on Response:" + response.errorBody()!!.string())
                        else -> {
                            Log.d(TAG, "on Response:" + response.toString())
                            val result: GetVerify = response.body()!!
                            activity_main_show_area_text.text = result.getShowDataSample()
                        }
                    }
                }

                override fun onFailure(call: Call<GetVerify>?, t: Throwable?) {
                    Log.d(TAG, "on onFailure:" + t.toString())
                }
            })
        }
    }

    fun getConsumer(): OkHttpOAuthConsumer {
        val consumer_key = ""
        val consumer_secret = ""
        val consumer = OkHttpOAuthConsumer(consumer_key, consumer_secret)
        consumer.setTokenWithSecret(PreferenceUtils.getToken(baseContext),
                PreferenceUtils.getTokenSecret(baseContext))
        return consumer
    }
}
