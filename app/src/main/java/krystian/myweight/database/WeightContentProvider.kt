package krystian.myweight.database

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.database.SQLException
import android.net.Uri
import android.util.Log
import com.raizlabs.android.dbflow.annotation.ConflictAction
import com.raizlabs.android.dbflow.config.FlowConfig
import com.raizlabs.android.dbflow.config.FlowManager
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper


/**
 * Created by Krystian on 2016-01-12.
 */
class WeightContentProvider : ContentProvider() {

    companion object {

        val TAG: String = "WeightContentProvider"

        internal const val AUTHORITY = "krystian.myweight.provider.weight"

        internal const val PATH_ALL = "all/"
        internal const val PATH_ALL_ORDER_BY_DATE_WEIGHT = "order_by_date_weight/"

        internal const val BASE_CONTENT_URI = "content://"

        internal const val URL_ALL = "$BASE_CONTENT_URI$AUTHORITY/$PATH_ALL"
        internal const val URL_ALL_ORDER_BY_DATE_WEIGHT = "$BASE_CONTENT_URI$AUTHORITY/$PATH_ALL_ORDER_BY_DATE_WEIGHT"

        internal val ALL = 1
        internal val ALL_ORDER_BY_DATE_WEIGHT = 2

        internal val uriMatcher: UriMatcher

        init {
            uriMatcher = UriMatcher(UriMatcher.NO_MATCH)
            uriMatcher.addURI(AUTHORITY, PATH_ALL, ALL)
            uriMatcher.addURI(AUTHORITY, PATH_ALL_ORDER_BY_DATE_WEIGHT, ALL_ORDER_BY_DATE_WEIGHT)
        }


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


    private var db: DatabaseWrapper? = null

    override fun onCreate(): Boolean {
        Log.d(TAG, "onCreate")
        FlowManager.init(FlowConfig.builder(context).build())
        val dbHelper = FlowManager.getDatabase(AppDatabase.NAME)

        /**
         * Create a write able database which will trigger its
         * creation if it doesn't already exist.
         */
        db = dbHelper.writableDatabase
        return if (db == null) false else true
    }

    override fun query(uri: Uri, projection: Array<String>?, selection: String?, selectionArgs: Array<String>?, sortOrder: String?): Cursor? {
        Log.d(TAG, "WeightContentProvider query")
        var cursor: Cursor?
        when (uriMatcher.match(uri)) {
            ALL -> cursor = db!!.query(WeightItem.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder)
            ALL_ORDER_BY_DATE_WEIGHT -> {
                cursor = db!!.query(WeightItem.TABLE_NAME, projection, selection, selectionArgs, null, null, WeightItem.TIME_MEASUREMENT + " DESC")
            }
            else -> throw IllegalArgumentException("Unknown URI " + uri)
        }

        cursor!!.setNotificationUri(context!!.contentResolver, uri)


        return cursor
    }

    override fun getType(uri: Uri): String? {
        when (uriMatcher.match(uri)) {
        /**
         * Get all records
         */
            ALL -> return URL_ALL
            ALL_ORDER_BY_DATE_WEIGHT -> return URL_ALL_ORDER_BY_DATE_WEIGHT

            else -> throw IllegalArgumentException("Unsupported URI: " + uri)
        }
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        Log.d(TAG, "WeightContentProvider insert")
        val adapter = FlowManager.getModelAdapter(FlowManager.getTableClassForName(AppDatabase.NAME, WeightItem.TABLE_NAME))
        val conflictAction = ConflictAction.getSQLiteDatabaseAlgorithmInt(adapter.insertOnConflictAction)
        val rowID = db!!.insertWithOnConflict(WeightItem.TABLE_NAME, "", values!!, conflictAction)

        if (rowID > 0) {
            context!!.contentResolver.notifyChange(uri, null)
            return uri
        }
        throw SQLException("Failed to add a record into " + uri)
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        return 0
    }

    override fun update(uri: Uri, values: ContentValues?, selection: String?, selectionArgs: Array<String>?): Int {
        return 0
    }

}
