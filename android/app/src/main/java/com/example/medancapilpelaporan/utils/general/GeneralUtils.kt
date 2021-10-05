package com.example.medancapilpelaporan.utils.general

import android.content.Context

object GeneralUtils {

    fun updateToken(token : String?, context: Context) {
        val userPreference = SessionManager(context)
        userPreference.saveSPString("__TOKEN__", token)
    }

    fun getToken(context: Context): String {
        val userPreference = SessionManager(context)
        return userPreference.token.toString()
    }

}