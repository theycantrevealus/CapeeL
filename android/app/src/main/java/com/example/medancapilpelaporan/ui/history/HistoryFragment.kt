package com.example.medancapilpelaporan.ui.history

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import app.disdukcapilmdn.sibisaapp.ui.utils.adapter.HistoryPelaporanListAdapter
import com.example.medancapilpelaporan.data.source.remote.network.ApiResponse
import com.example.medancapilpelaporan.databinding.FragmentHistoryBinding
import com.example.medancapilpelaporan.ui.ViewModelFactory
import com.example.medancapilpelaporan.ui.lapor.LaporLahirActivity
import com.example.medancapilpelaporan.ui.lapor.LaporMatiActivity
import com.example.medancapilpelaporan.ui.lapor.LaporPindahActivity
import com.example.medancapilpelaporan.ui.utils.click_listener.HistoryClickListener

class HistoryFragment : Fragment(), HistoryClickListener {

    private lateinit var viewModel: HistoryViewModel
    private var _binding: FragmentHistoryBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = HistoryPelaporanListAdapter()
        adapter.listener = this
        binding.rvPelaporan.layoutManager = LinearLayoutManager(view.context)
        binding.rvPelaporan.adapter = adapter

        viewModel = obtainViewModel(requireActivity() as AppCompatActivity)
        viewModel.getHistory()

        viewModel.listPelaporan.observe(viewLifecycleOwner, { response ->
            when(response) {
                is ApiResponse.Success -> {
                    response.data?.let {
                       if (it.size > 0) {
                           adapter.setList(it)
                           binding.rvPelaporan.visibility = View.VISIBLE
                           binding.riwayatKosong.visibility = View.GONE
                       } else {
                           binding.rvPelaporan.visibility = View.GONE
                           binding.riwayatKosong.visibility = View.VISIBLE
                       }
                    }
                    showLoading(false)
                }
                is ApiResponse.Error -> {
                    binding.rvPelaporan.visibility = View.GONE
                    binding.riwayatKosong.visibility = View.VISIBLE
                    showLoading(false)
                }
                is ApiResponse.Loading -> {
                    showLoading(true)
                }
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun obtainViewModel(activity: AppCompatActivity): HistoryViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory).get(HistoryViewModel::class.java)
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            binding.progressLayout.visibility = View.VISIBLE
            binding.riwayatKosong.visibility = View.GONE
            binding.rvPelaporan.visibility = View.GONE
        } else {
            binding.progressLayout.visibility = View.GONE
        }
    }

    override fun onHistoryItemClick(aksi: String, jenis: Int, id: Int) {
        when (jenis) {
            1 -> {
                val mIntent = Intent(activity?.applicationContext, LaporMatiActivity::class.java)
                mIntent.putExtra(LaporMatiActivity.AKSI, aksi)
                mIntent.putExtra(LaporMatiActivity.ID_LAPORAN, id)
                resultLauncher.launch(mIntent)
            }
            2 -> {
                val mIntent = Intent(activity?.applicationContext, LaporLahirActivity::class.java)
                mIntent.putExtra(LaporLahirActivity.AKSI, aksi)
                mIntent.putExtra(LaporLahirActivity.ID_LAPORAN, id)
                resultLauncher.launch(mIntent)
            }
            3 -> {
                val mIntent = Intent(activity?.applicationContext, LaporPindahActivity::class.java)
                mIntent.putExtra(LaporPindahActivity.AKSI, aksi)
                mIntent.putExtra(LaporPindahActivity.ID_LAPORAN, id)
                resultLauncher.launch(mIntent)
            }
        }
    }

    private val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data: Intent? = result.data
            showLoading(true)

            if (data != null) {
                val autoRefresh : Boolean = data.getBooleanExtra("auto_refresh", false)

                if (autoRefresh){
                    showLoading(true)
                    viewModel.getHistory()
                }
            }
        }
    }
}