package com.feluts.pregunta3.ui.dashboard

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.feluts.pregunta3.R
import com.feluts.pregunta3.databinding.FragmentDashboardBinding
import com.feluts.pregunta3.model.Pregunta
import kotlinx.coroutines.delay
import org.w3c.dom.Text
import java.lang.Exception
import kotlin.random.Random

class DashboardFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel
    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val titulo: TextView = binding.tittle
        val pregtxt: TextView = binding.pregTxt
        val btn1: Button = binding.opc1Btn
        val btn2: Button = binding.opc2Btn
        val btn3: Button = binding.opc3Btn
        val btn4: Button = binding.opc4Btn
        val btn_sig: Button = binding.sigBtn
        btn_sig.setVisibility(View.INVISIBLE)

        //tengo que ajustar esto de forma automatica
        //pasa que si lo hago a lo bobo termina rompiendo por un index out of range
        var y:IntArray = IntArray(15){it+1}
        y.shuffle()
        var pregs:ArrayList<Pregunta> = dashboardViewModel.getpreg(requireContext(),y[0])
        var preg = pregs.get(0)

        var incArr:MutableList<String> = preg.incorr.split(",").toMutableList()
        incArr.add(preg.corr)
        incArr.shuffle()

        //val randomVal = List(4){ Random.nextInt(0,4)}

        dashboardViewModel.text.observe(viewLifecycleOwner, Observer {
            titulo.text = "Pregunta3"
            try{
                pregtxt.text = preg.preg
                btn1.text = incArr[0]
                btn2.text = incArr[1]
                btn3.text = incArr[2]
                btn4.text = incArr[3]

            }catch (e:Exception){
                Log.e("Error", e.message.toString())
            }

        })
        //no es la forma mas eficiente pero funciona lo suficiente
        listeners(btn1,preg.corr)
        listeners(btn2,preg.corr)
        listeners(btn3,preg.corr)
        listeners(btn4,preg.corr)


        btn_sig.setOnClickListener(
            View.OnClickListener {
                y.shuffle()
                pregs = dashboardViewModel.getpreg(requireContext(),y[0])
                preg = pregs.get(0)
                incArr = preg.incorr.split(",").toMutableList()
                //bucar otras formas de splitear como el espacio
                incArr.add(preg.corr)
                incArr.shuffle()
                pregtxt.text = preg.preg
                //esta parte esta muy cochina
                btn1.setBackgroundColor(Color.BLUE)
                btn1.setTextColor(Color.WHITE)
                btn2.setBackgroundColor(Color.BLUE)
                btn2.setTextColor(Color.WHITE)
                btn3.setBackgroundColor(Color.BLUE)
                btn3.setTextColor(Color.WHITE)
                btn4.setBackgroundColor(Color.BLUE)
                btn4.setTextColor(Color.WHITE)
                //ademas de comprobar cual es el correcto tiene que cambiarlo de color de nuevo!! donde? como?
                btn1.text = incArr[0]
                btn2.text = incArr[1]
                btn3.text = incArr[2]
                btn4.text = incArr[3]
                listeners(btn1,preg.corr)
                listeners(btn2,preg.corr)
                listeners(btn3,preg.corr)
                listeners(btn4,preg.corr)
                btn_sig.setVisibility(View.INVISIBLE)
                //emprolijar esto
                //el boton de siguiente aparece despues de apretar otro!

            }
        )

        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    fun listeners(btn: Button, corr:String){
        btn.setOnClickListener(
            View.OnClickListener {
                if(btn.text == corr){
                    Toast.makeText(context,"Correcto ", Toast.LENGTH_LONG).show()
                    btn.setBackgroundColor(Color.GREEN)
                    btn.setTextColor(Color.BLACK)

                }else{
                    Toast.makeText(context,"Incorrecto ", Toast.LENGTH_LONG).show()
                    btn.setBackgroundColor(Color.RED)
                    btn.setTextColor(Color.BLACK)
                }
                binding.sigBtn.setVisibility(View.VISIBLE)

            }
        )
    }

    //btn: Button
    fun reload(){
        //val fm: FragmentManager = requireFragmentManager()!!.beginTransaction()
        //fm.detach(this).attach(this).commit()
        //Toast.makeText(context,"recarga2 ", Toast.LENGTH_LONG).show()
        //btn.setBackgroundColor(Color.BLUE)
        //btn.setTextColor(Color.WHITE)
        //childFragmentManager.beginTransaction().detach(this).attach(this).commit()
        parentFragmentManager.beginTransaction().detach(this).attach(this).commit()
    }
}

