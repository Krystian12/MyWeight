package krystian.myweight.ui.weight

import android.content.Context
import android.database.Cursor
import android.util.Log
import krystian.myweight.database.AppDatabase
import krystian.myweight.database.WeightContentProvider
import krystian.myweight.database.WeightItem
import krystian.myweight.unit.SharedPreferencesHelper
import java.util.*

/**
 * Created by Krystian on 2016-01-01.
 */
object WeightManager {

    private val TAG = "WeightManager"

    fun addEntries(weightItem: WeightItem) {
        Log.d(TAG, "addEntries: " + weightItem.toString())
        weightItem.save()
    }

    fun getLastWeight(context: Context): WeightItem? {
        val contentResolver = context.contentResolver

        val cursor = contentResolver.query(AppDatabase.WeightProvider.CONTENT_URI_ALL, null, null, null, null)

        var weightItem: WeightItem? = null

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                weightItem = loadEntrie(cursor)
            }
        }

        return weightItem
    }

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

    fun getDefaultUnit(): Weight.Unit {
        val unitStatusValue = SharedPreferencesHelper.getInt(SharedPreferencesHelper.KEY_START_UNIT)
        return WeightFactory.getUnitValue(unitStatusValue)
    }

}
