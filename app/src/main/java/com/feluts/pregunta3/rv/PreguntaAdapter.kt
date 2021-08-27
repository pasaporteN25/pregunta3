package com.feluts.pregunta3.rv

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.feluts.pregunta3.R
import com.feluts.pregunta3.dao.DBGestor
import com.feluts.pregunta3.model.Pregunta
import com.feluts.pregunta3.databinding.PregrvLayoutBinding
import com.feluts.pregunta3.model.Preguntaid
import com.feluts.pregunta3.ui.notifications.NotificationsViewModel
import kotlinx.coroutines.currentCoroutineContext
import java.lang.Exception

class PreguntaAdapter(val lista:ArrayList<Preguntaid>): RecyclerView.Adapter<PreguntaAdapter.ViewHolder>() {
    //private lateinit var nViewModel: NotificationsViewModel
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PreguntaAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.pregrv_layout, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return lista.size
    }

    override fun onBindViewHolder(holder: PreguntaAdapter.ViewHolder, position: Int) {
        holder.preg.setText(lista[position].preg)
        holder.corr.setText(lista[position].corr)
        holder.incorr.setText(lista[position].incorr)

        holder.guardar.setOnClickListener(
            View.OnClickListener {
                try{
                    val db: DBGestor = DBGestor(it.context,null)

                    if(db.modificarPreg(Preguntaid(holder.preg.id, holder.preg.text.toString(),holder.corr.text.toString(),holder.incorr.text.toString()))){
                        Toast.makeText(it.context,"Guardado",Toast.LENGTH_SHORT).show()
                    }
                }catch (e: Exception){
                    Log.e("Error al guardar modif", e.message.toString())}
            }
        )
    }



    class ViewHolder(view:View): RecyclerView.ViewHolder(view){
        val preg:EditText
        val corr:EditText
        val incorr:EditText
        val guardar:Button
        val reset:Button
        init {
            preg = view.findViewById(R.id.preg_et)
            corr = view.findViewById(R.id.corr_et)
            incorr = view.findViewById(R.id.incorr_et)
            guardar = view.findViewById(R.id.edit_btn)
            reset = view.findViewById(R.id.reset_btn)
        }
    }
}