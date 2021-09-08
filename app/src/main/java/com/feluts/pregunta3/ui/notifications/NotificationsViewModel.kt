package com.feluts.pregunta3.ui.notifications

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.feluts.pregunta3.dao.DBGestor
import com.feluts.pregunta3.model.Pregunta
import com.feluts.pregunta3.model.Preguntaid

class NotificationsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is notifications Fragment"
    }
    val text: LiveData<String> = _text


}