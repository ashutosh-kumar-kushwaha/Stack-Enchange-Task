package ashutosh.stackExchangeTask.time

import java.text.DateFormat
import java.util.Locale
import java.text.SimpleDateFormat
import java.util.Date
import java.util.concurrent.TimeUnit

class TimeFormat {

    fun getTimeDifference(dateString: String): String {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val date = dateFormat.parse(dateString)
        val now = Date()
        val diffInMillis = now.time - date.time

        val seconds = TimeUnit.MILLISECONDS.toSeconds(diffInMillis)
        if (seconds < 60) {
            return "just now"
        }

        val minutes = TimeUnit.MILLISECONDS.toMinutes(diffInMillis)
        if (minutes < 60) {
            return "$minutes ${getPluralSuffix(minutes, "minute")} ago"
        }

        val hours = TimeUnit.MILLISECONDS.toHours(diffInMillis)
        if (hours < 24) {
            return "$hours ${getPluralSuffix(hours, "hour")} ago"
        }

        val days = TimeUnit.MILLISECONDS.toDays(diffInMillis)
        if (days < 7) {
            return "$days ${getPluralSuffix(days, "day")} ago"
        }

        val weeks = days / 7
        if (weeks < 4) {
            return "$weeks ${getPluralSuffix(weeks, "week")} ago"
        }

        val months = days / 30
        if (months < 12) {
            return "$months ${getPluralSuffix(months, "month")} ago"
        }

        val years = days / 365
        return "$years ${getPluralSuffix(years, "year")} ago"
    }

    private fun getPluralSuffix(count: Long, singularSuffix: String): String {
        return if (count == 1L) {
            singularSuffix
        } else {
            "${singularSuffix}s"
        }
    }
}