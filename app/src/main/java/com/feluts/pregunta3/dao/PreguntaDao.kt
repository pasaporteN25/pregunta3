package com.feluts.pregunta3.dao

import androidx.room.*
import com.feluts.pregunta3.model.PreguntaR

@Dao
interface PreguntaDao {

    @Query("SELECT * FROM PreguntaR")
    fun getAll(): List<PreguntaR>

    @Query("SELECT * FROM PreguntaR WHERE id= :id")
    fun getById(id: Int): PreguntaR

    @Update
    fun update(pregunta: PreguntaR)

    @Insert
    fun insert(preg: List<PreguntaR>)

    @Delete
    fun delete(preg: PreguntaR)
}