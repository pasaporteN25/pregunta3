package com.feluts.pregunta3.ui.notifications

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.feluts.pregunta3.R
import com.feluts.pregunta3.databinding.FragmentNotificationsBinding
import com.feluts.pregunta3.databinding.ModificardbFragmentBinding
import com.feluts.pregunta3.rv.PreguntaAdapter

class ModificardbFragment : Fragment() {

    companion object {
        fun newInstance() = ModificardbFragment()
    }

    private lateinit var MviewModel: ModificardbViewModel
    //private var _binding: ModificardbFragmentBinding? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.modificardb_fragment, container, false)
        //MviewModel = ViewModelProvider(this).get(MviewModel::class.java)
        //_binding = ModificardbFragmentBinding.inflate(inflater, container, false)

        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        MviewModel = ViewModelProvider(this).get(ModificardbViewModel::class.java)

        val pregrv: RecyclerView = requireView().findViewById(R.id.items_rv)
        pregrv.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        val FragmentManager = requireFragmentManager()
        pregrv.adapter = PreguntaAdapter(MviewModel.getPregs(requireActivity()),FragmentManager)
        pregrv.adapter?.notifyDataSetChanged()

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }


}