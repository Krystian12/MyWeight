package krystian.myweight.ui

import android.app.Application
import com.raizlabs.android.dbflow.config.FlowConfig
import com.raizlabs.android.dbflow.config.FlowLog
import com.raizlabs.android.dbflow.config.FlowManager
import krystian.myweight.unit.SharedPreferencesHelper

/**
 * Created by Krystian on 2017-09-20.
 */
class AppApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        //FlowManager.init(FlowConfig.Builder(this).build())
        //FlowLog.setMinimumLoggingLevel(FlowLog.Level.V);
        SharedPreferencesHelper.init(this)
    }
}