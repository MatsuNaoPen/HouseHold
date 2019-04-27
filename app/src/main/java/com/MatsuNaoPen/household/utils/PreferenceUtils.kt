package com.MatsuNaoPen.household.utils

import android.content.Context
import android.content.Context.MODE_PRIVATE

/**
 * Created by DevUser on 2017/12/17.
 */
class PreferenceUtils() {

    companion object {
        val PREFERENCE_NAME = "PREFERENCE_NAME"
        val TOKEN_KEY = "TOKEN_KEY"
        val TOKEN_SECRET_KEY = "TOKEN_SECRET_KEY"

        fun setToken(context: Context, token: String) {

            val pref = context.getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE)

            pref.edit().putString(TOKEN_KEY, token).apply()
        }

        fun getToken(context: Context): String {
            val pref = context.getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE)
            return pref.getString(TOKEN_KEY, "")
        }

        fun setTokenSecret(context: Context, TokenSecret: String) {

            val pref = context.getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE)

            pref.edit().putString(TOKEN_SECRET_KEY, TokenSecret).apply()
        }

        fun getTokenSecret(context: Context): String {
            val pref = context.getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE)
            return pref.getString(TOKEN_SECRET_KEY, "")
        }
    }
}
