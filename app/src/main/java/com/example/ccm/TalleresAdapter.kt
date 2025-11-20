package com.example.dentigest

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

// El adaptador recibe la lista de talleres y una función lambda para manejar clics
class TalleresAdapter(private val talleres: List<Taller>, private val onItemClicked: (Taller) -> Unit) :
    RecyclerView.Adapter<TalleresAdapter.TallerViewHolder>() {

    // 1. ViewHolder: Contiene las referencias a los elementos de la vista de cada ítem
    class TallerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.tv_taller_title)
        val description: TextView = itemView.findViewById(R.id.tv_taller_description)
        val icon: ImageView = itemView.findViewById(R.id.iv_taller_icon)

        fun bind(taller: Taller, onItemClicked: (Taller) -> Unit) {
            title.text = taller.titulo
            description.text = taller.descripcion
            icon.setImageResource(taller.iconoResId)

            itemView.setOnClickListener {
                onItemClicked(taller)
            }
        }
    }

    // 2. Crea nuevos ViewHolders (infla el layout del ítem)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TallerViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_category_card, parent, false)
        return TallerViewHolder(view)
    }

    // 3. Reemplaza el contenido de un ViewHolder
    override fun onBindViewHolder(holder: TallerViewHolder, position: Int) {
        holder.bind(talleres[position], onItemClicked)
    }

    // 4. Retorna el tamaño de tu dataset
    override fun getItemCount() = talleres.size
}
