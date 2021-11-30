package ni.edu.uca.rentapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import ni.edu.uca.rentapp.Entidades.Casa
import ni.edu.uca.rentapp.databinding.ListaCasaBinding

class CasaAdapter(val context : Context, val dataset : List<Casa>) :
    RecyclerView.Adapter<CasaAdapter.CasaViewHolder>() {


    inner class CasaViewHolder(private val itemCasaBinding: ListaCasaBinding)
        : RecyclerView.ViewHolder(itemCasaBinding.root) {

        fun bind(casa : Casa)
        {
            with(itemCasaBinding) {
                tvDescripcionBreve.text = casa.descripcionB
                imagenCasa.setImageURI(casa.foto.toUri())
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CasaViewHolder {
        val itemCasaBinding = ListaCasaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CasaViewHolder(itemCasaBinding = itemCasaBinding)
    }

    override fun onBindViewHolder(holder: CasaViewHolder, position: Int) = holder.bind(dataset[position])

    override fun getItemCount(): Int = dataset.size

}