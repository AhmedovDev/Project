package ru.diitcenter.optovik.data.prefs

import android.content.SharedPreferences

class PrefsHelper(private val sharedPrefs: SharedPreferences) {

    private val PREF_ADDRESS = "address"
    private val PREF_PHONE = "phone"
    private val PREF_TOKEN = "token"
    private val PREF_DELIVERY = "delivery"


    fun getAddress(): String? = sharedPrefs.getString(PREF_ADDRESS, null)

    fun saveAddress(phone: String) = sharedPrefs.edit()
        .putString(PREF_ADDRESS, phone)
        .apply()

    fun clearAddress() = sharedPrefs.edit()
        .remove(PREF_ADDRESS)
        .apply()


    fun getDelivery(): String? = sharedPrefs.getString(PREF_DELIVERY, null)

    fun saveDelivery(phone: String) = sharedPrefs.edit()
        .putString(PREF_DELIVERY, phone)
        .apply()

    fun getPhone(): String? = sharedPrefs.getString(PREF_PHONE, null)

    fun savePhone(phone: String) = sharedPrefs.edit()
        .putString(PREF_PHONE, phone)
        .apply()

    fun getToken(): String? = sharedPrefs.getString(PREF_TOKEN, null)

    fun saveToken(token: String) = sharedPrefs.edit()
        .putString(PREF_TOKEN, token)
        .apply()

    fun clearToken() = sharedPrefs.edit()
    .remove(PREF_TOKEN)
    .apply()


    fun clearPhone() = sharedPrefs.edit()
        .remove(PREF_PHONE)
        .apply()

}