package com.feluts.pregunta3.ui.notifications

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.feluts.pregunta3.R
import com.feluts.pregunta3.databinding.AgregaradbFragmentBinding
import com.feluts.pregunta3.databinding.FragmentNotificationsBinding
import com.feluts.pregunta3.model.Pregunta
import com.feluts.pregunta3.model.Preguntaid

class AgregaradbFragment : Fragment() {

    companion object {
        //fun newInstance() = AgregaradbFragment()
        fun newInstance(preg: Preguntaid) = AgregaradbFragment().apply {
            arguments = Bundle().apply {
                putInt("id",preg.id)
                putString("pregunta",preg.preg)
                putString("correcta",preg.corr)
                putString("incorrecta",preg.incorr)

            }
        }

    }

    private lateinit var addViewModel: AgregaradbViewModel
    private var _binding: AgregaradbFragmentBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = AgregaradbFragmentBinding.inflate(inflater, container, false)
        val root: View = binding.root

        //modificar elcomportamiento del boton, uno guarda y el otro actualiza!

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        addViewModel = ViewModelProvider(this).get(AgregaradbViewModel::class.java)

        if (arguments?.getInt("id")!=null){
            binding.pregTodb.setText(arguments?.getString("pregunta"))
            binding.corrTodb.setText(arguments?.getString("correcta"))
            binding.incorrTodb.setText(arguments?.getString("incorrecta"))
            //lo copie a los campos numa
            binding.sendTodb.setOnClickListener(
                View.OnClickListener {
                    if(addViewModel.modificar(Preguntaid(arguments?.getInt("id").toString().toInt(),binding.pregTodb.text.toString(),binding.corrTodb.text.toString(),binding.incorrTodb.text.toString()),requireContext())){
                        Toast.makeText(requireContext(),"Guardado",Toast.LENGTH_SHORT).show()
                    }else{
                        Toast.makeText(requireContext(),"Error al guardar",Toast.LENGTH_SHORT).show()
                    }
                }
            )

        }else{
            binding.sendTodb.setOnClickListener(
                View.OnClickListener {
                    if(addViewModel.nuevapregunta(Pregunta(binding.pregTodb.text.toString(),binding.corrTodb.text.toString(),binding.incorrTodb.text.toString()),requireContext())){
                        Toast.makeText(requireContext(),"Guardado",Toast.LENGTH_SHORT).show()
                    }else{
                        Toast.makeText(requireContext(),"Error al guardar",Toast.LENGTH_SHORT).show()
                    }
                }
            )

        }

    }

}