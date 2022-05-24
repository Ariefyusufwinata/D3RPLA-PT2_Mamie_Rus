package d3ifcool.bisapetcah.mamierus.core.datastore

import android.content.Context
import android.content.SharedPreferences

class PreferencesData (mContext : Context) {

    private val PREFS_NAME = "session_login"
    private var sharedPrefs : SharedPreferences
    private val editor : SharedPreferences.Editor

    init {
        sharedPrefs = mContext.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        editor = sharedPrefs.edit()
    }

    fun put(key : String, value : String) {
        editor.putString(key, value)
            .apply()
    }

    fun getString(key : String) : String? {
        return sharedPrefs.getString(key, null)
    }

    fun put(key : String, value : Boolean) {
        editor.putBoolean(key, value)
            .apply()
    }

    fun getBoolean(key : String) : Boolean {
        return sharedPrefs.getBoolean(key, false)
    }

    fun clear() {
        editor.clear()
            .apply()
    }
}