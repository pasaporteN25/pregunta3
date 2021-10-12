package com.feluts.pregunta3.ui.home

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.annotation.AnimRes
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.navOptions
import com.feluts.pregunta3.R

class UserScreen : Fragment() {

    private val args by navArgs<UserScreenArgs>()

    companion object {
        fun newInstance() = UserScreen()
    }

    private lateinit var viewModel: UserScreenViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.user_screen_fragment, container, false)

        val btn_back = view.findViewById<Button>(R.id.logout_btn)
        val user_msj = view.findViewById<TextView>(R.id.user_show)
        val puntos_msj = view.findViewById<TextView>(R.id.puntos_show)
        user_msj.text = "Bienvenido "+args.usuarioCuenta.usuario
        puntos_msj.text = "0"

        btn_back.setOnClickListener(
            View.OnClickListener {
                //val navop = NavOptions.Builder()
                //navop.setPopUpTo(R.id.userScreen,false).build()
                val action: NavDirections = UserScreenDirections.actionUserScreenToNavigationHome()
                findNavController().navigate(action)
            }
        )

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(UserScreenViewModel::class.java)
        // TODO: Use the ViewModel
    }

}