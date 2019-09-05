package ru.diitcenter.optovik.data.prefs

import android.content.SharedPreferences

class PrefsHelper(private val sharedPrefs: SharedPreferences) {

    private val PREF_ADDRESS = "address"
    private val PREF_PHONE = "phone"


    fun getAddress(): String? = sharedPrefs.getString(PREF_ADDRESS, null)

    fun saveAddress(phone: String) = sharedPrefs.edit()
        .putString(PREF_ADDRESS, phone)
        .apply()

    fun clearAddress() = sharedPrefs.edit()
        .remove(PREF_ADDRESS)
        .apply()



    fun getPhone(): String? = sharedPrefs.getString(PREF_PHONE, null)

    fun savePhone(phone: String) = sharedPrefs.edit()
        .putString(PREF_PHONE, phone)
        .apply()

    fun clearPhone() = sharedPrefs.edit()
        .remove(PREF_PHONE)
        .apply()

}