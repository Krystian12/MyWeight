package krystian.myweight.ui.weight

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.util.Log
import krystian.myweight.database.WeightContentProvider
import krystian.myweight.database.WeightItem
import krystian.myweight.unit.SharedPreferencesHelper
import java.util.*

/**
 * Created by Krystian on 2016-01-01.
 */
object WeightManager {


    fun addEntries(weightItem: WeightItem) {
        val contentValues = ContentValues()
        Log.d(TAG, "addEntries: " + weightItem.toString())
        weightItem.save()
    }

    fun getLastWeight(context: Context): WeightItem? {
        val contentResolver = context.contentResolver

        val cursor = contentResolver.query(WeightContentProvider.CONTENT_URI_ALL_ORDER_BY_DATE_WEIGHT, null, null, null, null)

        var weightItem: WeightItem? = null
        if (cursor!!.moveToFirst()) {
            weightItem = loadEntrie(cursor)
        }

        return weightItem
    }

    val dateNow: Date
        get() = Calendar.getInstance().time

    private val TAG = "WeightManager"

    private var weightManager: WeightManager? = null


    fun loadEntrie(cursor: Cursor): WeightItem {
        val weightItem = WeightItem()

        weightItem.id = cursor.getLong(WeightContentProvider.KEY_INT_ID)
        weightItem.timeAdd = Date(cursor.getLong(WeightContentProvider.KEY_INT_TIME_ADD))
        weightItem.timeChange = Date(cursor.getLong(WeightContentProvider.KEY_INT_TIME_CHANGE))
        weightItem.timeMeasurement = Date(cursor.getLong(WeightContentProvider.KEY_INT_TIME_MEASUREMENT))


        Log.d(TAG, "loadEntrie: " + weightItem.toString())

        return weightItem

    }

    fun getDefaultWeight(): Weight {
        val unitStatusValue = SharedPreferencesHelper.getInt(SharedPreferencesHelper.KEY_START_UNIT)
        return WeightFactory.getWightValue(unitStatusValue)
    }

    fun getDefaultUnit() : Weight.Unit{
        val unitStatusValue = SharedPreferencesHelper.getInt(SharedPreferencesHelper.KEY_START_UNIT)
        return WeightFactory.getUnitValue(unitStatusValue)
    }

}
