package krystian.myweight.database

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.database.SQLException
import android.net.Uri
import com.raizlabs.android.dbflow.annotation.ConflictAction
import com.raizlabs.android.dbflow.config.FlowManager
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper


/**
 * Created by Krystian on 2016-01-12.
 */
class WeightContentProvider : ContentProvider() {

    companion object {

        internal val PROVIDER_NAME = "krystian.provider.weight"
        private val PATH_ALL = "all"
        private val PATH_ALL_ORDER_BY_DATE_WEIGHT = "order_by_date_weight"

        internal val URL_ALL = "content://$PROVIDER_NAME/$PATH_ALL"
        internal val URL_ALL_ORDER_BY_DATE_WEIGHT = "content://$PROVIDER_NAME/$PATH_ALL_ORDER_BY_DATE_WEIGHT"

        val CONTENT_URI_ALL = Uri.parse(URL_ALL)
        val CONTENT_URI_ALL_ORDER_BY_DATE_WEIGHT = Uri.parse(URL_ALL_ORDER_BY_DATE_WEIGHT)

        internal val ALL = 1
        internal val ALL_ORDER_BY_DATE_WEIGHT = 2

        internal val uriMatcher: UriMatcher

        init {
            uriMatcher = UriMatcher(UriMatcher.NO_MATCH)
            uriMatcher.addURI(PROVIDER_NAME, PATH_ALL, ALL)
            uriMatcher.addURI(PROVIDER_NAME, PATH_ALL_ORDER_BY_DATE_WEIGHT, ALL_ORDER_BY_DATE_WEIGHT)
        }

    }


    private var db: DatabaseWrapper? = null

    override fun onCreate(): Boolean {
        val dbHelper = FlowManager.getDatabase(AppDatabase.NAME)

        /**
         * Create a write able database which will trigger its
         * creation if it doesn't already exist.
         */
        db = dbHelper.writableDatabase
        return if (db == null) false else true
    }

    override fun query(uri: Uri, projection: Array<String>?, selection: String?, selectionArgs: Array<String>?, sortOrder: String?): Cursor? {

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
            ALL -> return "vnd.android.cursor.dir/krystian.provider.weight"
            ALL_ORDER_BY_DATE_WEIGHT -> return "vnd.android.cursor.dir/krystian.provider.weight_order"

            else -> throw IllegalArgumentException("Unsupported URI: " + uri)
        }
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
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
