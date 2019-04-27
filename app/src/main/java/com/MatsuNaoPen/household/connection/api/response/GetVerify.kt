package com.MatsuNaoPen.household.connection.api.response

/**
 * Created by DevUser on 2017/12/21.
 */
data class GetVerify(
        private val me: Me,
        private val time: Time) {

    fun getShowDataSample(): String {
        val builder = StringBuffer()
                .append("Me").append("\r\n")
                .append("id:").append(me.id).append("\r\n")
                .append("login:").append(me.login).append("\r\n")
                .append("name:").append(me.name).append("\r\n")
                .append("input_count:").append(me.input_count).append("\r\n")
                .append("day_count:").append(me.day_count).append("\r\n")
                .append("repeat_count:").append(me.repeat_count).append("\r\n")
                .append("day:").append(me.day).append("\r\n")
                .append("week:").append(me.week).append("\r\n")
                .append("month:").append(me.month).append("\r\n")
                .append("currency_code:").append(me.currency_code).append("\r\n")
                .append("profile_image_url:").append(me.profile_image_url).append("\r\n")
                .append("cover_image_url:").append(me.cover_image_url).append("\r\n")
                .append("currency_code:").append(me.currency_code).append("\r\n")
                .append("profile_modified:").append(me.profile_modified).append("\r\n")
//                .append("time:").append("\r\n")
//                .append("requested:").append(time.requested).append("\r\n")
        return builder.toString()
    }
}

data class Me(
        val id: String,
        val login: String,
        val name: String,
        val input_count: String,
        val day_count: String,
        val repeat_count: String,
        val day: String,
        val week: String,
        val month: String,
        val currency_code: String,
        val profile_image_url: String,
        val cover_image_url: String,
        val profile_modified: String
)

data class Time(
        val requested: String
)
