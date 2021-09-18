package com.example.medancapilpelaporan.ui.system

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.medancapilpelaporan.Config
import com.example.medancapilpelaporan.MainActivity
import com.example.medancapilpelaporan.databinding.ActivityLoginBinding
import com.example.medancapilpelaporan.utils.general.InputUtils
import com.example.medancapilpelaporan.utils.general.RetroInstance
import com.example.medancapilpelaporan.utils.general.SessionManager
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Headers
import retrofit2.http.POST

class LoginActivity: AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    lateinit var sessionManager: SessionManager
    private val configuration: Config = Config()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sessionManager = SessionManager(this)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener(View.OnClickListener {
            val username = binding.txtUsername.text.toString().trim()
            val password = binding.txtPassword.text.toString().trim()
            when {
                TextUtils.isEmpty(username) -> binding.txtUsername.error = InputUtils.FIELD_REQUIRED
                else -> {
                    login(username, password, "", this)
                }
            }
        })
    }

    interface ApiInterface {
        @Headers("Accept: application/json")
        @POST("Pegawai")
        @FormUrlEncoded
        fun signin(
            @Field("request") request: String,
            @Field("username") email: String,
            @Field("password") password: String
        ): Call<Login>
    }

    class Login {
        @SerializedName("response_data")
        @Expose
        private val response_data: UserData ?= null

        fun getResponseData(): UserData? {
            return response_data
        }
    }

    class UserData {
        @SerializedName("uid")
        @Expose
        private val uid: String? = null

        @SerializedName("username")
        @Expose
        private val username: String? = null

        @SerializedName("nama")
        @Expose
        private val nama: String? = null

        @SerializedName("no_handphone")
        @Expose
        private val contact: String? = null

        @SerializedName("email")
        @Expose
        private val email: String? = null

        @SerializedName("password")
        @Expose
        private val password: String? = null

        fun getUID(): String? {
            return uid
        }

        fun getEmail(): String? {
            return email
        }

        fun getUserName(): String? {
            return username
        }

        fun getContact(): String? {
            return contact
        }

        fun getNama(): String? {
            return nama
        }

        fun getPassword(): String? {
            return password
        }
    }

    private fun login(email: String, password: String, token: String?, context: Context) {
        var retIn: ApiInterface = RetroInstance.getRetrofitInstance(configuration.serverAPI).create(
            ApiInterface::class.java
        )

        retIn.signin("login", email, password).enqueue(object : Callback<Login> {
            override fun onFailure(call: Call<Login>, t: Throwable) {
                Log.e("TANAKA","Failure")
            }

            override fun onResponse(call: Call<Login>, response: Response<Login>) {
                //Toast.makeText(parentFragment, "Kode : " + response.code().toString(), Toast.LENGTH_LONG).show()
                Log.e("TANAKA", "Test")
                Log.e("TANAKA", response.code().toString())

                if (response.code() == 200) {
                    var dataset: Login? = response.body()
                    sessionManager.saveSPString("__UID__", dataset?.getResponseData()?.getUID())
                    sessionManager.saveSPString("__NAME__", dataset?.getResponseData()?.getNama())
                    sessionManager.saveSPString("__USERNAME__", dataset?.getResponseData()?.getUserName())
                    sessionManager.saveSPString("__HP__", dataset?.getResponseData()?.getContact())
                    sessionManager.saveSPString("__EMAIL__", dataset?.getResponseData()?.getEmail())
                    sessionManager.saveSPString("__PASSWORD__", dataset?.getResponseData()?.getPassword())

                    if (!sessionManager.uID.equals("") || sessionManager.uID?.isEmpty()!!) {
                        val mIntent = Intent(context, MainActivity::class.java)
                        startActivity(mIntent)
                    }
                } else if (response.code() == 400) {
                    //
                } else {
                    //
                }
                call.cancel()
            }
        })
    }
}