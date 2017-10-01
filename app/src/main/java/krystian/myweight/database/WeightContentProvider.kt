package krystian.myweight.database

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import android.util.Log
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
        Log.d(TAG, "insert UriL " + uri.toString())
        notifyChange()
        return uri
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        Log.d(TAG, "WeightContentProvider delete")
        notifyChange()
        return 1
    }

    override fun update(uri: Uri, values: ContentValues?, selection: String?, selectionArgs: Array<String>?): Int {
        Log.d(TAG, "WeightContentProvider update")
        notifyChange()
        return 1
    }

    private fun notifyChange() {
        context!!.contentResolver.notifyChange(AppDatabase.WeightProvider.CONTENT_URI_ALL, null)
        context!!.contentResolver.notifyChange(AppDatabase.WeightProvider.CONTENT_URI_ALL_ORDER_BY_DATE_WEIGHT, null)
    }

}
