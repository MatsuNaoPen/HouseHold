package com.MatsuNaoPen.household.connection

/**
 * Created by DevUser on 2017/12/21.
 */
enum class ReadEventList(val activation: Boolean) {
    VERIFY(true),
    HOME_MONEY(true),
    HOME_CATEGORY(true),
    HOME_GENRE(false),
    HOME_ACCOUNT(true),
    ACCOUNT(false);
}