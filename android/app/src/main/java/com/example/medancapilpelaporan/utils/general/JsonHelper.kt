package com.example.medancapilpelaporan.utils.general

import com.example.medancapilpelaporan.data.source.remote.response.HistoryPelaporan
import org.json.JSONException
import org.json.JSONObject

object JsonHelper {

    fun historyJsonToEntities(jsonString: String): ArrayList<HistoryPelaporan> {
        val list = ArrayList<HistoryPelaporan>()

        try {
            val responseArray = JSONObject(jsonString).getJSONArray("content")

            for (i in 0 until responseArray.length()) {

                val history = responseArray.getJSONObject(i)

                val id = history.getInt("id")
                val idJenis = history.getInt("id_jenis")
                val namaJenis = history.getString("nama_jenis")
                val nik = history.getString("nik")
                val nama = history.getString("nama")
                val waktuSubmit = ""//history.getString("created_at")

                val historyEntities = HistoryPelaporan(id, idJenis, namaJenis, nik, nama, waktuSubmit)
                list.add(historyEntities)
            }

        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return list
    }

}