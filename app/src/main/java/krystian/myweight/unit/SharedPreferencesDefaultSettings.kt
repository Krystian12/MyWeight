package krystian.myweight.unit

import java.util.*

/**
 * Created by Krystian on 2016-01-06.
 */
class SharedPreferencesDefaultSettings {

    private val defaultSettingsMap: HashMap<String, Any>

    init {
        defaultSettingsMap = HashMap()
    }

    fun addDefaultSettingsString(key: String, value: String) {
        defaultSettingsMap.put(key, value)
    }

    fun addDefaultSettingsInt(key: String, value: Int) {
        defaultSettingsMap.put(key, value)
    }

    fun getDefaultSettings(key: String): Any? {
        return defaultSettingsMap[key]
    }

    fun getDefaultSettingsString(key: String): String {
        return getDefaultSettings(key) as String
    }

    fun getDefaultSettingsInt(key: String): Int {
        return getDefaultSettings(key) as Int
    }
}
