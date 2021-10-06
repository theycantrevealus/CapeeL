package com.example.medancapilpelaporan.ui.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.medancapilpelaporan.databinding.FragmentHomeBinding
import com.example.medancapilpelaporan.ui.history.HistoryViewerActivity
import com.example.medancapilpelaporan.ui.lapor.LaporLahirActivity
import com.example.medancapilpelaporan.ui.lapor.LaporMatiActivity
import com.example.medancapilpelaporan.ui.lapor.LaporPindahActivity
import com.example.medancapilpelaporan.ui.notifications.NotificationViewerActivity
import com.example.medancapilpelaporan.ui.profile.ProfileEditActivity
import com.example.medancapilpelaporan.utils.general.GeneralUtils

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        homeViewModel =
                ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val msgNoAkses = "Maaf, Anda tidak memiliki akses"
        val idAkses = GeneralUtils.getAkses(root.context)
        binding.namaUser.text = GeneralUtils.getNama(root.context)

        binding.profileImage.setOnClickListener {
            val mIntent = Intent(context, ProfileEditActivity::class.java)
            startActivity(mIntent)
        }

        binding.menuLaporMati.setOnClickListener {
            val mIntent = Intent(context, LaporMatiActivity::class.java)
            mIntent.putExtra(LaporMatiActivity.AKSI, "add")
            startActivity(mIntent)
        }

        binding.menuLaporLahir.setOnClickListener {
            if (idAkses == 6) {
                showAlertDialog(msgNoAkses, root.context)
            } else {
                val mIntent = Intent(context, LaporLahirActivity::class.java)
                mIntent.putExtra(LaporLahirActivity.AKSI, "add")
                startActivity(mIntent)
            }
        }

        binding.menuLaporPindah.setOnClickListener {
            if (idAkses == 7) {
                val mIntent = Intent(context, LaporPindahActivity::class.java)
                mIntent.putExtra(LaporPindahActivity.AKSI, "add")
                startActivity(mIntent)
            } else {
                showAlertDialog(msgNoAkses, root.context)
            }
        }

        binding.btnLaporan.setOnClickListener {
            val mIntent = Intent(root.context, HistoryViewerActivity::class.java)
            startActivity(mIntent)
        }

        binding.btnSetting.setOnClickListener {
            val mIntent = Intent(root.context, NotificationViewerActivity::class.java)
            startActivity(mIntent)
        }

        return root
    }

    private fun showAlertDialog(msg: String, context: Context) {
        val alertDialogBuilder = AlertDialog.Builder(context)
        val dialogTitle = "Informasi"

        alertDialogBuilder.setTitle(dialogTitle)
        alertDialogBuilder
            .setMessage(msg)
            .setCancelable(false)
            .setPositiveButton("Oke") { dialog, id -> dialog.cancel() }

        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}