package krystian.myweight.database

import android.net.Uri
import com.raizlabs.android.dbflow.annotation.Database
import com.raizlabs.android.dbflow.annotation.provider.ContentProvider
import com.raizlabs.android.dbflow.annotation.provider.ContentUri
import com.raizlabs.android.dbflow.annotation.provider.TableEndpoint

@Database(name = AppDatabase.NAME, version = AppDatabase.VERSION, generatedClassSeparator = "_")
object AppDatabase {
    const val NAME: String = "AppDatabase"
    const val VERSION: Int = 1

    object WeightProvider {
        val CONTENT_URI_ALL = Uri.parse(WeightContentProvider.URL_ALL)
        val CONTENT_URI_ALL_ORDER_BY_DATE_WEIGHT = Uri.parse(WeightContentProvider.URL_ALL_ORDER_BY_DATE_WEIGHT)


        val COLUMNS_ALL = arrayOf<String>(
                WeightItem._ID,
                WeightItem.TIME_ADD,
                WeightItem.TIME_CHANGE,
                WeightItem.TIME_MEASUREMENT,
                WeightItem.WEIGHT_OF_GRAM,
                WeightItem.WEIGHT_TO_DISPLAY_IN_KILOGRAMS,
                WeightItem.WEIGHT_TO_DISPLAY_IN_FUNT,
                WeightItem.WEIGHT_TO_DISPLAY_IN_STONE
        )

        val KEY_INT_ID = 0
        val KEY_INT_TIME_ADD = KEY_INT_ID + 1
        val KEY_INT_TIME_CHANGE = KEY_INT_TIME_ADD + 1
        val KEY_INT_TIME_MEASUREMENT = KEY_INT_TIME_CHANGE + 1
        val KEY_INT_WEIGHT_OF_GRAM = KEY_INT_TIME_MEASUREMENT + 1
        val KEY_INT_WEIGHT_TO_DISPLAY_IN_KILOGRAMS = KEY_INT_WEIGHT_OF_GRAM + 1
        val KEY_INT_WEIGHT_TO_DISPLAY_IN_FUNT = KEY_INT_WEIGHT_TO_DISPLAY_IN_KILOGRAMS + 1
        val KEY_INT_WEIGHT_TO_DISPLAY_IN_STONE = KEY_INT_WEIGHT_TO_DISPLAY_IN_FUNT + 1
    }
}