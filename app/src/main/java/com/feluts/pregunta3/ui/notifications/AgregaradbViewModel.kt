package com.feluts.pregunta3.ui.notifications

import android.content.Context
import androidx.lifecycle.ViewModel
import com.feluts.pregunta3.dao.DBGestor
import com.feluts.pregunta3.model.Pregunta
import com.feluts.pregunta3.model.Preguntaid

class AgregaradbViewModel : ViewModel() {
    fun nuevapregunta(pregunta: Pregunta, context: Context):Boolean{
        val db: DBGestor = DBGestor(context, null)
        if(db.guardarPregunta(pregunta)){
            return true
        }else{
            return false
        }
    }

    fun modificar(pregunta: Preguntaid,context: Context):Boolean{
        //tendria que pasar contexto??
        val db: DBGestor = DBGestor(context,null)
        if(db.modificarPreg(pregunta)){
            return true
        }else{
            return false
        }

    }
}