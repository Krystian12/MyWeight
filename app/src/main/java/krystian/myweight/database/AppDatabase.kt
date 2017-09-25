package krystian.myweight.database

import android.net.Uri
import com.raizlabs.android.dbflow.annotation.Database
import com.raizlabs.android.dbflow.annotation.provider.ContentProvider
import com.raizlabs.android.dbflow.annotation.provider.ContentUri
import com.raizlabs.android.dbflow.annotation.provider.TableEndpoint
import com.raizlabs.android.dbflow.structure.provider.ContentUtils.BASE_CONTENT_URI



@ContentProvider(authority = WeightContentProvider.AUTHORITY,
        database = AppDatabase::class,
        baseContentUri = WeightContentProvider.BASE_CONTENT_URI  )
@Database(name = AppDatabase.NAME, version = AppDatabase.VERSION, generatedClassSeparator = "_")
object AppDatabase {
    const val NAME: String = "AppDatabase"
    const val VERSION: Int = 1

    @TableEndpoint(name = WeightItem.TABLE_NAME, contentProvider = AppDatabase::class)
    object WeightProvider{
        @ContentUri(path = WeightItem.TABLE_NAME, type = ContentUri.ContentType.VND_MULTIPLE + WeightItem.TABLE_NAME)
        val CONTENT_URI_ALL = buildUri(WeightItem.TABLE_NAME)

//        @ContentUri(path = WeightContentProvider.PATH_ALL_ORDER_BY_DATE_WEIGHT, type = ContentUri.ContentType.VND_MULTIPLE + WeightContentProvider.PATH_ALL_ORDER_BY_DATE_WEIGHT)
//        val CONTENT_URI_ALL_ORDER_BY_DATE_WEIGHT = buildUri(WeightContentProvider.PATH_ALL_ORDER_BY_DATE_WEIGHT)
    }

    private fun buildUri(vararg paths: String): Uri {
        val builder = Uri.parse(WeightContentProvider.BASE_CONTENT_URI + WeightContentProvider.AUTHORITY).buildUpon()
        for (path in paths) {
            builder.appendPath(path)
        }
        return builder.build()
    }

}