package krystian.myweight.unit

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import krystian.myweight.ui.weight.Weight

/**
 * Created by Krystian on 2016-01-03.
 */
object SharedPreferencesHelper {

    val KEY_START_UNIT = "start_unit"

    private lateinit var sharedPreferencesDefaultSettings: SharedPreferencesDefaultSettings

    private lateinit var sharedPreferences: SharedPreferences

    fun init(context: Context) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        sharedPreferencesDefaultSettings = SharedPreferencesDefaultSettings()
        setupDefault()
    }

    private fun setupDefault() {
        sharedPreferencesDefaultSettings.addDefaultSettingsInt(KEY_START_UNIT, Weight.Unit.KILOGRAM.valueStatus)
    }

    fun putString(key: String, value: String) {
        sharedPreferences.edit().putString(key, value)
    }

    fun putInt(key: String, value: Int) {
        sharedPreferences.edit().putInt(key, value)
    }

    fun commit() {
        sharedPreferences.edit().commit()
    }

    fun getString(key: String): String? {
        return sharedPreferences.getString(key, sharedPreferencesDefaultSettings.getDefaultSettingsString(key))
    }

    fun getInt(key: String): Int {
        return sharedPreferences.getInt(key, sharedPreferencesDefaultSettings.getDefaultSettingsInt(key))
    }

}
