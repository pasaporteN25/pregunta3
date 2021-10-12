package com.feluts.pregunta3.ui.dashboard

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.feluts.pregunta3.dao.DBGestor
import com.feluts.pregunta3.model.Pregunta
import com.feluts.pregunta3.model.Pregunta2
import com.feluts.pregunta3.model.PreguntaR
import com.feluts.pregunta3.model.Preguntaid
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException

class DashboardViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is dashboard Fragment"
    }
    val text: LiveData<String> = _text

    fun getpreg(context: Context,p: Int):ArrayList<Pregunta>{
        val db: DBGestor = DBGestor(context, null)
        return db.getPregunta(p)
    }

    private fun getJsonDataFromAsset(context: Context, fileName: String = "preguntas.json"): String? {
        val jsonString: String
        try {
            jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
        }
        return jsonString
    }

    fun getPreguntas(context: Context): List<Pregunta2> {
        val jsonString = getJsonDataFromAsset(context)
        val listProductType = object : TypeToken<List<Pregunta2>>() {}.type
        return Gson().fromJson(jsonString, listProductType)
    }
}