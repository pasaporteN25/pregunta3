package com.feluts.pregunta3.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PreguntaR(
    @PrimaryKey(autoGenerate = true)
    var id:Int,
    var preg:String, var corr:String, var incorr:String)