package com.feluts.pregunta3.ui.dashboard

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.feluts.pregunta3.dao.DBGestor
import com.feluts.pregunta3.model.Pregunta

class DashboardViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is dashboard Fragment"
    }
    val text: LiveData<String> = _text

    fun getpreg(context: Context,p: Int):ArrayList<Pregunta>{
        val db: DBGestor = DBGestor(context, null)
        return db.getPregunta(p)
    }
}