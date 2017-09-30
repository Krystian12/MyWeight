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

        val cursor = contentResolver.query(AppDatabase.WeightProvider.CONTENT_URI_ALL_ORDER_BY_DATE_WEIGHT, null, null, null, null)

        var weightItem: WeightItem? = null

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                weightItem = WeightItem(cursor)
            }
        }

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
