package krystian.myweight.database

import android.content.Context
import android.os.Environment
import android.util.Log
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

/**
 * Shayan Rais (http://shanraisshan.com)
 * created on 8/17/2016
 */
object DatabaseUtil {
    //You need to declare permission
    // <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
    //in your Manifest file in order to use this class

    //______________________________________________________________________________________________

    //todo -> rename the database according to your application
    internal val DATABASE_NAME = AppDatabase.NAME + ".db"
    //example WhatsApp :  /data/data/com.whatsapp/databases/msgstore.db
    internal val FOLDER_EXTERNAL_DIRECTORY = Environment.getExternalStorageDirectory().toString()

    //______________________________________________________________________________________________
    private val TAG: String = "DatabaseUtil"

    /**
     * Call this method from any activity in your app (
     * for example ->    DatabaseUtil.copyDatabaseToExtStg(MainActivity.this);
     * this method will copy the database of your application into SDCard folder "shanraisshan/MyDatabase.sqlite" (DATABASE_NAME)
     */
    fun copyDatabaseToExtStg(ctx: Context) {
        //external storage file
        val externalDirectory = File(FOLDER_EXTERNAL_DIRECTORY)
        if (!externalDirectory.exists())
            externalDirectory.mkdirs()
        val toFile = File(externalDirectory, DATABASE_NAME)
        Log.d(TAG, "toFile: " + toFile)
        //internal storage file
        //https://developer.android.com/reference/android/content/Context.html#getDatabasePath(java.lang.String)
        val fromFile = ctx.getDatabasePath(DATABASE_NAME)
        Log.d(TAG, "fromFile: " + fromFile)
        //example WhatsApp :  /data/data/com.whatsapp/databases/msgstore.db
        if (fromFile.exists())
            Log.d(TAG, "fromFile exists ")
         copy(fromFile, toFile)
    }


    //______________________________________________________________________________________________ Utility function
    /**
     * @param fromFile source location
     * @param toFile destination location
     * copy file from 1 location to another
     */
    internal fun copy(fromFile: File, toFile: File) {
        try {
            val `is` = FileInputStream(fromFile)
            val src = `is`.channel
            val os = FileOutputStream(toFile)
            val dst = os.channel
            dst.transferFrom(src, 0, src.size())
            src.close()
            `is`.close()
            dst.close()
            os.close()
        } catch (e: Exception) {
            Log.e(TAG, "copy files", e)
        }

    }
}