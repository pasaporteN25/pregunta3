package com.feluts.pregunta3.ui.notifications

import android.content.Context
import androidx.lifecycle.ViewModel
import com.feluts.pregunta3.dao.DBGestor
import com.feluts.pregunta3.model.Preguntaid

class ModificardbViewModel : ViewModel() {
    fun getPregs(context: Context):ArrayList<Preguntaid>{
        val db: DBGestor = DBGestor(context, null)
        return db.getAllPreg()
    }
}