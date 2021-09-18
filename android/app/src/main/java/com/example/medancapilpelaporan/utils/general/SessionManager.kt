package com.example.medancapilpelaporan.utils.general

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Bitmap
import com.example.medancapilpelaporan.Config

class SessionManager {
    private val configuration: Config = Config()
    private lateinit var sp: SharedPreferences
    val spEditor: SharedPreferences.Editor
    constructor(context: Context) {
        sp = context.getSharedPreferences("DISDUKCAPIL_PELAPORAN", 0)
        spEditor = sp.edit()
    }

    fun saveSPString(keySP: String?, value: String?) {
        spEditor.putString(keySP, value)
        spEditor.commit()
    }

    fun saveSPInt(keySP: String?, value: Int) {
        spEditor.putInt(keySP, value)
        spEditor.commit()
    }

    fun saveSPBoolean(keySP: String?, value: Boolean) {
        spEditor.putBoolean(keySP, value)
        spEditor.commit()
    }

    var uID: String?
        get() = sp.getString("__UID__", "")
        set(value) {
            uID = value
        }

    var username: String?
        get() = sp.getString("__USERNAME__", "")
        set(value) {
            username = value
        }

    var kontak: String?
        get() = sp.getString("__HP__", "")
        set(value) {
            kontak = value
        }

    var nama: String?
        get() = sp.getString("__NAMA__", "")
        set(value) {
            nama = value
        }

    var email: String?
        get() = sp.getString("__EMAIL__", "")
        set(value) {
            email = value
        }


    var foto: String?
        get() = sp.getString("__FOTO__", "")
        set(value) {
            foto = value
        }

    var token: String?
        get() = sp.getString("__TOKEN__", "")
        set(value) {
            token = value
        }

    fun logout() {
        spEditor.clear().commit()
    }
}