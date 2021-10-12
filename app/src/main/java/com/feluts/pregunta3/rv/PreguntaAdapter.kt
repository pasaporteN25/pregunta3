package com.feluts.pregunta3.rv

import android.content.Context
import android.content.Intent
import android.text.Layout
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.feluts.pregunta3.R
import com.feluts.pregunta3.dao.DBGestor
import com.feluts.pregunta3.model.Pregunta
import com.feluts.pregunta3.model.Preguntaid
import com.feluts.pregunta3.ui.notifications.AgregaradbFragment
import com.feluts.pregunta3.ui.notifications.ModificardbFragment
import com.feluts.pregunta3.ui.notifications.NotificationsFragment
import com.feluts.pregunta3.ui.notifications.NotificationsViewModel
import kotlinx.coroutines.currentCoroutineContext
import org.w3c.dom.Text
import java.lang.Exception

class PreguntaAdapter(val lista:ArrayList<Preguntaid>, fm: FragmentManager, val listener: () -> Unit): RecyclerView.Adapter<PreguntaAdapter.ViewHolder>() {

    //private lateinit var nViewModel: NotificationsViewModel
    var hfm: FragmentManager = fm
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PreguntaAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.preg_layout, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return lista.size
    }

    override fun onBindViewHolder(holder: PreguntaAdapter.ViewHolder, position: Int) {
        holder.preg.setText(lista[position].preg)
        holder.corr.setText(lista[position].corr)
        holder.incorr.setText(lista[position].incorr)
        val ident: Int = lista[position].id
        val tomod = Preguntaid(ident,holder.preg.text.toString(),holder.corr.text.toString(),holder.incorr.text.toString())
        holder.itemView.setOnClickListener(
            View.OnClickListener {
                val addfdb: AgregaradbFragment = AgregaradbFragment.newInstance(tomod)
                val transac = hfm.beginTransaction()
                transac.replace(R.id.nav_host_fragment_activity_main,addfdb).addToBackStack(null).commit()
                //aca seria mejor hacerlo con un callback y un listener

                listener()

            }
        )

    }



    class ViewHolder(view:View): RecyclerView.ViewHolder(view){
        val preg:TextView
        val corr:TextView
        val incorr:TextView

        init {
            preg = view.findViewById(R.id.preg_card)
            corr = view.findViewById(R.id.corr_card)
            incorr = view.findViewById(R.id.incorr_card)

        }
    }
}