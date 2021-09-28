package com.example.medancapilpelaporan.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.medancapilpelaporan.databinding.FragmentHomeBinding
import com.example.medancapilpelaporan.ui.lapor.LaporLahirActivity
import com.example.medancapilpelaporan.ui.lapor.LaporMatiActivity
import com.example.medancapilpelaporan.ui.lapor.LaporPindahActivity
import com.example.medancapilpelaporan.ui.profile.ProfileEditActivity

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
    ): View? {
        homeViewModel =
                ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root


        /*val textView: TextView = binding.textHome
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })*/



        binding.profileImage.setOnClickListener(View.OnClickListener {
            val mIntent = Intent(context, ProfileEditActivity::class.java)
            startActivity(mIntent)
        })

        binding.menuLaporMati.setOnClickListener(View.OnClickListener {
            val mIntent = Intent(context, LaporMatiActivity::class.java)
            startActivity(mIntent)
        })

        binding.menuLaporLahir.setOnClickListener(View.OnClickListener {
            val mIntent = Intent(context, LaporLahirActivity::class.java)
            startActivity(mIntent)
        })

        binding.menuLaporPindah.setOnClickListener(View.OnClickListener {
            val mIntent = Intent(context, LaporPindahActivity::class.java)
            startActivity(mIntent)
        })

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}