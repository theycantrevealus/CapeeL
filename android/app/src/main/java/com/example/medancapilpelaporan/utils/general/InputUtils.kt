package com.example.medancapilpelaporan.utils.general

import android.text.TextUtils
import android.util.Patterns

object InputUtils {

    const val FIELD_REQUIRED = "Harus diisi"
    const val WRONG_FORMAT = "Format salah"

    //email checker
    fun isValidEmail(email: String): Boolean {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    //string length checker
    fun checkStringLength(str: String, expectedLength: Int): Boolean {
        return str.length == expectedLength
    }
}