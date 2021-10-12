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
import com.feluts.pregunta3.model.Pregunta2
import com.feluts.pregunta3.model.Preguntaid
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.delay
import org.w3c.dom.Text
import java.lang.Exception
import kotlin.random.Random

class DashboardFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel
    private var _binding: FragmentDashboardBinding? = null
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

        //Variables de vista
        val titulo: TextView = binding.tittle
        val pregtxt: TextView = binding.pregTxt
        val btn1: Button = binding.opc1Btn
        val btn2: Button = binding.opc2Btn
        val btn3: Button = binding.opc3Btn
        val btn4: Button = binding.opc4Btn
        val btn_sig: Button = binding.sigBtn
        //var pregPosition: Int = 0
        var cont: Int = 0
        btn_sig.setVisibility(View.INVISIBLE)

        //Traigo los datos del json y los acomodo:
        val datos: List<Pregunta2> = dashboardViewModel.getPreguntas(root.context)
        val y: IntArray = IntArray(25) { it } //revisar el index out of range
        y.shuffle()
        val incArr: MutableList<String> =
            datos[y[cont]].incorrectas.split(",").toMutableList()
        incArr.add(datos[y[cont]].correcta)
        incArr.shuffle()

        //val randomVal = List(4){ Random.nextInt(0,4)}

        dashboardViewModel.text.observe(viewLifecycleOwner, Observer {
            titulo.text = "Pregunta3"
            try {
                //Acomodo los datos en los botones
                pregtxt.text = datos[y[cont]].pregunta
                btn1.text = incArr[0]
                btn2.text = incArr[1]
                btn3.text = incArr[2]
                btn4.text = incArr[3]

            } catch (e: Exception) {
                Log.e("Error", e.message.toString())
            }

        })
        //Seteo los comportamientos de los botones al jugar
        listeners(btn1, datos[y[cont]].correcta)
        listeners(btn2, datos[y[cont]].correcta)
        listeners(btn3, datos[y[cont]].correcta)
        listeners(btn4, datos[y[cont]].correcta)

        //Pasar a la siguiente pregunta:
        //Resetear todas las vistas
        btn_sig.setOnClickListener(
            View.OnClickListener {
                if(cont<24) {
                    cont += 1
                    //pregPosition = +1
                    //desbloqueo los botones
                    bloquearBtn(binding.opc1Btn, true)
                    bloquearBtn(binding.opc2Btn, true)
                    bloquearBtn(binding.opc3Btn, true)
                    bloquearBtn(binding.opc4Btn, true)

                    var incArr: MutableList<String> =
                        datos[y[cont]].incorrectas.split(",").toMutableList()
                    incArr.add(datos[y[cont]].correcta)
                    incArr.shuffle()

                    coloresBtn(btn1)
                    coloresBtn(btn2)
                    coloresBtn(btn3)
                    coloresBtn(btn4)

                    pregtxt.text = datos[y[cont]].pregunta
                    btn1.text = incArr[0]
                    btn2.text = incArr[1]
                    btn3.text = incArr[2]
                    btn4.text = incArr[3]
                    //tendria que pasar todos los botones o manejar esto de alguna forma mejor

                    listeners(btn1, datos[y[cont]].correcta)
                    listeners(btn2, datos[y[cont]].correcta)
                    listeners(btn3, datos[y[cont]].correcta)
                    listeners(btn4, datos[y[cont]].correcta)
                    btn_sig.setVisibility(View.INVISIBLE)

                }else{
                    Snackbar.make(root, "Felicidades, has ganado!", Snackbar.LENGTH_LONG)
                        .show()
                    bloquearBtn(binding.opc1Btn, false)
                    bloquearBtn(binding.opc2Btn, false)
                    bloquearBtn(binding.opc3Btn, false)
                    bloquearBtn(binding.opc4Btn, false)
                }

                //Si se termina deberia fijarme que onda el puntaje no??
                //agregar un contador o algo


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

    fun bloquearBtn(btn: Button, eq: Boolean) {
        btn.isEnabled = eq
        btn.isClickable = eq
    }

    fun coloresBtn(btn: Button) {
        btn.setBackgroundColor(Color.BLUE)
        btn.setTextColor(Color.WHITE)
    }


    fun listeners(btn: Button, corr: String) {
        btn.setOnClickListener(
            View.OnClickListener {
                if (btn.text == corr) {
                    Toast.makeText(context, "Correcto ", Toast.LENGTH_SHORT).show()
                    btn.setBackgroundColor(Color.GREEN)
                    btn.setTextColor(Color.BLACK)

                } else {
                    Toast.makeText(context, "Incorrecto ", Toast.LENGTH_SHORT).show()
                    btn.setBackgroundColor(Color.RED)
                    btn.setTextColor(Color.BLACK)
                }
                binding.sigBtn.setVisibility(View.VISIBLE)
                bloquearBtn(binding.opc1Btn, false)
                bloquearBtn(binding.opc2Btn, false)
                bloquearBtn(binding.opc3Btn, false)
                bloquearBtn(binding.opc4Btn, false)

            }
        )
    }

}

