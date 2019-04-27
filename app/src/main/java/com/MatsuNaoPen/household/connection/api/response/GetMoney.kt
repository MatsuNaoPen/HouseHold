package com.MatsuNaoPen.household.connection.api.response

import java.io.Serializable
import java.lang.StringBuilder

/**
 * Created by DevUser on 2017/12/17.
 */
data class GetMoney(
        private val money: List<Money>) : Serializable {
    fun getShowDataSample(): String {
        val builder = StringBuilder()
        builder.append("count:").append(money.size).append(", show latest 100\r\n")
        for (i in 1..100) {
            builder.append(money[i].date).append(":").append(money[i].name).append(" \u00A5").append(money[i].amount).append("\r\n")
        }
        return builder.toString()
    }
}

data class Money(
        val id: String,
        val mode: String,
        val user_ud: String,
        val date: String,
        val category_id: String,
        val genre_id: String,
        val to_account_id: String,
        val from_account_id: String,
        val amount: String,
        val comment: String,
        val active: String,
        val name: String,
        val receipt_id: String,
        val place: String,
        val created: String,
        val currency_code: String

) : Serializable
