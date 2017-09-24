package krystian.myweight.unit

import android.content.Context

import java.text.DateFormat
import java.util.Date

/**
 * Created by Krystian on 2015-12-30.
 */
object DateFormater {

    fun getDateTimeDefault(context: Context, date: Date): String {
        val dateFormat = android.text.format.DateFormat.getDateFormat(context)

        return dateFormat.format(date)
    }

    fun getDateTimeDefault(date: Date?): String? {
        if (date == null) return null

        val dateFormat = DateFormat.getDateTimeInstance()
        return dateFormat.format(date)
    }

}
