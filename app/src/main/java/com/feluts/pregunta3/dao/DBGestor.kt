package com.feluts.pregunta3.dao

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.feluts.pregunta3.model.Pregunta
import com.feluts.pregunta3.model.Preguntaid
import java.lang.Exception


class DBGestor(context: Context, factory: SQLiteDatabase.CursorFactory?):SQLiteOpenHelper(context, DB_NAME, factory, DB_VERS) {
    companion object{
        //private val DBEXT_NAME = "preguntas1.db"
        //val DBLOCATION = "/data/data/com.your.packagename/databases/"
        private val DB_NAME = "preg.db"
        private val DB_VERS = 1
        val TABLA1 = "preguntas"
        val COLUMN_ID = "id"
        val COLUMN_PREG = "pregunta"
        val COLUMN_CORR = "corr_resp"
        val COLUMN_INCORR = "incorr_resp"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val crearTabla1 =
            ("CREATE TABLE "+ TABLA1 + " ( " + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_PREG + " TEXT NOT NULL, " +
                    COLUMN_CORR + " TEXT NOT NULL, " +
                    COLUMN_INCORR + " TEXT NOT NULL ) ")
        db?.execSQL(crearTabla1)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }

    //funciones
    fun guardarPregunta(pregunta: Pregunta):Boolean{
        try{
            val db = this.writableDatabase
            val values = ContentValues()
            //values.put(COLUMN_ID, pregunta.id)
            values.put(COLUMN_PREG, pregunta.preg)
            values.put(COLUMN_CORR, pregunta.corr)
            values.put(COLUMN_INCORR, pregunta.incorr)
            db.insert(TABLA1,null,values)
            return true
            //Log.e("Guardado ${pregunta.preg}","prueba")
        } catch (e: Exception){
            Log.e("Error al insertar: ", e.message.toString())
            return false
        }
    }

    fun getPregunta(col:Int):ArrayList<Pregunta>{
        val db = this.readableDatabase
        val pregunta: ArrayList<Pregunta> = ArrayList<Pregunta>()
        val query = "SELECT * FROM $TABLA1 WHERE $COLUMN_ID=$col"
        val cursor = db.rawQuery(query,null)
        if(cursor.moveToNext()){
            val preg = cursor.getString(cursor.getColumnIndex(COLUMN_PREG))
            val corr = cursor.getString(cursor.getColumnIndex(COLUMN_CORR))
            val incorr = cursor.getString(cursor.getColumnIndex(COLUMN_INCORR))
            pregunta.add(Pregunta(preg,corr,incorr))
        }
        cursor.close()
        return pregunta
    }

    fun getAllPreg():ArrayList<Preguntaid>{
        val db = this.readableDatabase
        val pregunta: ArrayList<Preguntaid> = ArrayList<Preguntaid>()
        val query = "SELECT * FROM $TABLA1"
        val cursor = db.rawQuery(query,null)
        if(cursor.moveToNext()){
            do {
                val id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID))
                val preg = cursor.getString(cursor.getColumnIndex(COLUMN_PREG))
                val corr = cursor.getString(cursor.getColumnIndex(COLUMN_CORR))
                val incorr = cursor.getString(cursor.getColumnIndex(COLUMN_INCORR))
                pregunta.add(Preguntaid(id,preg,corr,incorr))
            }while(cursor.moveToNext())
        }
        cursor.close()
        return pregunta
    }

    fun modificarPreg(pregunta: Preguntaid):Boolean{
        try{
            val db = this.writableDatabase
            val values = ContentValues()
            values.put(COLUMN_PREG, pregunta.preg)
            values.put(COLUMN_CORR, pregunta.corr)
            values.put(COLUMN_INCORR, pregunta.incorr)
            val where = "$COLUMN_ID=?"
            val whereargs = arrayOf(pregunta.id.toString())
            db.update(TABLA1, values, where, whereargs)
            Log.e("Modificado!", pregunta.corr)
            return true
        }catch (e: Exception){
            Log.e("Error al modificar", e.message.toString())
            return false
        }
    }

}