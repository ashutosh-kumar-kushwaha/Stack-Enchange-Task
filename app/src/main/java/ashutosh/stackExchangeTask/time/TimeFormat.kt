package ashutosh.stackExchangeTask.time

import java.text.DateFormat
import java.util.Locale
import java.text.SimpleDateFormat
import java.util.Date
import java.util.concurrent.TimeUnit

class TimeFormat {
    fun Int.toTime(): String{
        return DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.getDefault()).format(this)
    }

    fun getTimeDifference(dateString: String): String {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val date = dateFormat.parse(dateString) ?: return "Incorrect format of date"
        val now = Date()
        val diffInMillis = now.time - date.time

        val seconds = TimeUnit.MILLISECONDS.toSeconds(diffInMillis)
        if (seconds < 60) {
            return "just now"
        }

        val minutes = TimeUnit.MILLISECONDS.toMinutes(diffInMillis)
        if (minutes < 60) {
            return "$minutes minutes ago"
        }

        val hours = TimeUnit.MILLISECONDS.toHours(diffInMillis)
        if (hours < 24) {
            return "$hours hours ago"
        }

        val days = TimeUnit.MILLISECONDS.toDays(diffInMillis)
        if (days < 7) {
            return "$days days ago"
        }

        val weeks = days / 7
        if (weeks < 4) {
            return "$weeks weeks ago"
        }

        val months = days / 30
        if (months < 12) {
            return "$months months ago"
        }

        val years = days / 365
        return "$years years ago"
    }
}