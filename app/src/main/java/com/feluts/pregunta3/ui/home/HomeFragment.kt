package com.feluts.pregunta3.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.feluts.pregunta3.R
import com.feluts.pregunta3.databinding.FragmentHomeBinding

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

        val textView: TextView = binding.textHome
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            //textView.text = it
        })

        binding.loginImg.setOnClickListener(
            View.OnClickListener {
                var chng:Boolean = false
                if (chng == false){
                    binding.loginImg.setImageResource(R.drawable.ic_headset_user)
                    chng = true
                }else{
                    binding.loginImg.setImageResource(R.drawable.ic_baseline_image_24)
                    chng = false
                }
                Toast.makeText(it.context,"click",Toast.LENGTH_SHORT).show()
            }
        )

        binding.signinBtn.setOnClickListener(
            View.OnClickListener {
                val regdb = RegistroFragment()
                val transaction: FragmentTransaction = requireFragmentManager().beginTransaction()
                transaction.replace(R.id.nav_host_fragment_activity_main,regdb).addToBackStack(null).commit()
            }
        )

        return root
    }

    //override fun OnVi

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}