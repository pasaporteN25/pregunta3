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
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.fragment.findNavController
import com.feluts.pregunta3.R
import com.feluts.pregunta3.databinding.FragmentHomeBinding
import com.feluts.pregunta3.model.Usuario
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton


class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    lateinit var mGoogleSignInClient: GoogleSignInClient
    //no recuerdo que hacia esta parte
    val RC_SIGN_IN=123
    //
    var chng:Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        //val textView: TextView = binding.textHome
        //homeViewModel.text.observe(viewLifecycleOwner, Observer {
            //textView.text = it
        //})

        //chng siempre queda en false, hay que arreglar eso
        binding.loginImg.setOnClickListener(
            View.OnClickListener {

                if (chng == false){
                    binding.loginImg.setImageResource(R.drawable.ic_headset_user)
                    chng = true
                }else{
                    binding.loginImg.setImageResource(R.drawable.ic_baseline_image_24)
                    chng = false
                }
                //Toast.makeText(it.context,"click",Toast.LENGTH_SHORT).show()
            }
        )

        binding.signinBtn.setOnClickListener(
            View.OnClickListener {
                val regdb = RegistroFragment()
                val transaction: FragmentTransaction = requireFragmentManager().beginTransaction()
                transaction.replace(R.id.nav_host_fragment_activity_main,regdb).addToBackStack(null).commit()
            }
        )

        binding.loginBtn.setOnClickListener(
            View.OnClickListener {
                if(binding.userIn.text.toString()==""){
                    binding.userIn.setError("Ingrese usuario")
                }else{if(binding.passIn.text.toString()==""){
                    binding.passIn.setError("Contraseña invalida")
                }else{
                    //por aca tengo que mandar la info
                    //primero deberia validar que la info es real pero bueno x ahora...
                    //val userfm= UserScreen()

                    //usando navigations:
                    val usuario = Usuario(binding.userIn.text.toString(),binding.passIn.text.toString())
                    //realmente no necesitaba pasar esta info aun... usar firebase...
                    //val navop = NavOptions.Builder()
                    //navop.setPopUpTo(R.id.userScreen,false).build()
                    val action:NavDirections = HomeFragmentDirections.actionNavigationHomeToUserScreen(usuario)
                    findNavController().navigate(action)
                }}

            }
        )

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build()
        mGoogleSignInClient = GoogleSignIn.getClient(this.context, gso)

        binding.googleSignInBtn.setSize(SignInButton.SIZE_STANDARD)
        binding.googleSignInBtn.setOnClickListener(
            View.OnClickListener {
                val signInIntent = mGoogleSignInClient.signInIntent
                startActivityForResult(signInIntent, RC_SIGN_IN)


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