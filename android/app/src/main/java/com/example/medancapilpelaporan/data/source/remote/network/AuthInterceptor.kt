package com.example.medancapilpelaporan.data.source.remote.network

import android.content.Context
import com.example.medancapilpelaporan.utils.general.GeneralUtils
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(val context: Context): Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()

        //custom function in object to get token
        GeneralUtils.getToken(context).let {
            requestBuilder.addHeader("Authorization", "Bearer $it")
        }

        return chain.proceed(requestBuilder.build())
    }
}