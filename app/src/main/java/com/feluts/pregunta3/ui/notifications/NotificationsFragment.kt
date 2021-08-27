package com.feluts.pregunta3.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.feluts.pregunta3.R
import com.feluts.pregunta3.databinding.FragmentNotificationsBinding
import com.feluts.pregunta3.model.Pregunta
import com.feluts.pregunta3.rv.PreguntaAdapter

class NotificationsFragment : Fragment() {

    private lateinit var notificationsViewModel: NotificationsViewModel
    private var _binding: FragmentNotificationsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    var preg_in:String = ""
    var corr_in:String = ""
    var incorr_in:String = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        notificationsViewModel = ViewModelProvider(this).get(NotificationsViewModel::class.java)

        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textNotifications
        notificationsViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = "Ingreso preguntas"
        })

        val preg: EditText = binding.pregIn
        val corr:EditText = binding.corrIn
        val incorr:EditText = binding.incorrIn
        //limpiar los campos
        val guardar: Button = binding.guardarBtn
        val verbtn:Button = binding.verdbBtn
        val prv:RecyclerView = binding.dbRv
        prv.setVisibility(View.INVISIBLE)

        guardar.setOnClickListener(
            View.OnClickListener {
                if(notificationsViewModel.nuevapregunta(Pregunta(preg.text.toString(),corr.text.toString(),incorr.text.toString()),it.context)){
                    Toast.makeText(root.context,"Guardado", Toast.LENGTH_LONG).show()
                }else{
                    Toast.makeText(root.context,"Error al guardar", Toast.LENGTH_LONG).show()
                }
            }
        )

        verbtn.setOnClickListener(
            View.OnClickListener {
                prv.setVisibility(View.VISIBLE)
            }
        )

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val pregrv:RecyclerView = binding.dbRv
        pregrv.layoutManager = LinearLayoutManager(context,RecyclerView.VERTICAL, false)
        pregrv.adapter = PreguntaAdapter(notificationsViewModel.getPregs(requireActivity()))
        pregrv.adapter?.notifyDataSetChanged()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}