package com.example.medancapilpelaporan.ui.notifications

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.medancapilpelaporan.Config
import com.example.medancapilpelaporan.MainActivity
import com.example.medancapilpelaporan.R
import com.example.medancapilpelaporan.databinding.FragmentNotificationsBinding
import com.example.medancapilpelaporan.ui.profile.ProfileEditActivity
import com.example.medancapilpelaporan.ui.system.LoginActivity
import com.example.medancapilpelaporan.utils.general.InputUtils
import com.example.medancapilpelaporan.utils.general.RetroInstance
import com.example.medancapilpelaporan.utils.general.SessionManager
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import io.github.muddz.styleabletoast.StyleableToast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Headers
import retrofit2.http.POST

class NotificationsFragment : Fragment() {

    private lateinit var notificationsViewModel: NotificationsViewModel
    private var _binding: FragmentNotificationsBinding? = null
    private val binding get() = _binding!!
    lateinit var sessionManager: SessionManager
    private val configuration: Config = Config()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        notificationsViewModel =
                ViewModelProvider(this).get(NotificationsViewModel::class.java)

        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        sessionManager = SessionManager(root.context)

        val profileNama: EditText = binding.profileNama
        val profileEmail: EditText = binding.profileEmail
        val profileKontak: EditText = binding.profileKontak
        notificationsViewModel.text.observe(viewLifecycleOwner, Observer {
            profileNama.setText(sessionManager.nama.toString())
            profileEmail.setText(sessionManager.email.toString())
            profileKontak.setText(sessionManager.kontak.toString())
        })

        binding.btnProfileLogout.setOnClickListener(View.OnClickListener {
            sessionManager.logout()
            val mIntent = Intent(context, LoginActivity::class.java)
            startActivity(mIntent)
            activity?.finish()
        })

        binding.btnProfileUpdate.setOnClickListener(View.OnClickListener {
            val nama = binding.profileNama.text.toString().trim()
            val email = binding.profileEmail.text.toString().trim()
            val kontak = binding.profileKontak.text.toString().trim()
            var old = binding.profilePasswordOld.text.toString().trim()
            var new = binding.profilePasswordNew.text.toString().trim()
            var conf = binding.profilePasswordConf.text.toString().trim()

            when{
                TextUtils.isEmpty(nama) -> binding.profileNama.error = InputUtils.FIELD_REQUIRED
                TextUtils.isEmpty(email) -> binding.profileEmail.error = InputUtils.FIELD_REQUIRED
                TextUtils.isEmpty(kontak) -> binding.profileKontak.error = InputUtils.FIELD_REQUIRED
                (!new.equals(conf)) -> binding.profilePasswordNew.error = InputUtils.FIELD_REQUIRED
                else -> {
                    updateProfile(nama, email, kontak, old, new)
                }
            }
        })

        return root
    }


    interface ApiInterface {
        @Headers("Accept: application/json")
        @POST("Pegawai")
        @FormUrlEncoded
        fun signin(
            @Field("request") request: String,
            @Field("nama") nama: String,
            @Field("email") email: String,
            @Field("no_handphone") no_handphone: String,
            @Field("old") old: String,
            @Field("new") new: String
        ): Call<UpdateResponse>
    }

    class UpdateResponse {
        @SerializedName("response_data")
        @Expose
        private val response_data: ArrayList<UserData>? = null

        @SerializedName("response_token")
        @Expose
        private val response_token: String ?= null

        @SerializedName("response_result")
        @Expose
        private val response_result: Int = 0

        @SerializedName("response_message")
        @Expose
        private val response_message: String = ""

        fun getResponseData(): ArrayList<UserData>? {
            return response_data
        }

        fun getResponseResult(): Int {
            return response_result
        }

        fun getResponseMessage(): String {
            return response_message
        }

        fun getResponseToken(): String? {
            return response_token
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

    fun updateProfile(nama: String, email: String, kontak: String, old: String, new: String) {
        var retIn: ApiInterface = RetroInstance.getRetrofitInstance(configuration.serverAPI).create(
            ApiInterface::class.java
        )

        retIn.signin("update_profile", nama, email,kontak,old,new).enqueue(object : Callback<UpdateResponse> {
            override fun onFailure(call: Call<UpdateResponse>, t: Throwable) {
                val message: String? = t.message
                Log.e("TANAKA", message.toString())
            }

            override fun onResponse(call: Call<UpdateResponse>, response: Response<UpdateResponse>) {
                if (response.code() == 200) {
                    var dataset: UpdateResponse? = response.body()
                    if(dataset?.getResponseResult()!! > 0) {
                        sessionManager.saveSPString("__UID__", dataset.getResponseData()!![0].getUID())
                        sessionManager.saveSPString("__NAMA__", dataset.getResponseData()!![0].getNama())
                        sessionManager.saveSPString("__USERNAME__", dataset.getResponseData()!![0].getUserName())
                        sessionManager.saveSPString("__HP__", dataset.getResponseData()!![0].getContact())
                        sessionManager.saveSPString("__EMAIL__", dataset.getResponseData()!![0].getEmail())
                        sessionManager.saveSPString("__PASSWORD__", dataset.getResponseData()!![0].getPassword())
                        sessionManager.saveSPString("__TOKEN__", dataset.getResponseToken())
                    } else {
                        StyleableToast.makeText(activity!!.applicationContext, dataset.getResponseMessage(), Toast.LENGTH_LONG, R.style.toast_warning).show();
                    }
                }
                call.cancel()
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}