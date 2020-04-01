package id.alpine.coronainformation.ui.kasus_daerah

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.alpine.coronainformation.R
import id.alpine.coronainformation.model.ResponseDaerah
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.list_item_info_daerah.view.*

class DaerahAdapter : RecyclerView.Adapter<DaerahAdapter.VH>() {

    private val listItem = mutableListOf<ResponseDaerah>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DaerahAdapter.VH = VH(
        LayoutInflater.from(parent.context).inflate(
            R.layout.list_item_info_daerah, parent, false
        )
    )

    override fun getItemCount(): Int = listItem.size

    override fun onBindViewHolder(holder: DaerahAdapter.VH, position: Int) {
        holder.bindItem(listItem[position])
    }

    fun updateData(data: MutableList<ResponseDaerah>) {
        listItem.clear()
        listItem.addAll(data)
        notifyDataSetChanged()
    }

    class VH(itemView: View) : RecyclerView.ViewHolder(itemView), LayoutContainer {
        override val containerView: View?
            get() = itemView

        fun bindItem(data: ResponseDaerah) {
            itemView.tv_nama_daerah.text = data.attributes?.provinsi.toString()
            itemView.tv_positif.text = data.attributes?.kasusPosi.toString()
            itemView.tv_sembuh.text = data.attributes?.kasusSemb.toString()
            itemView.tv_meninggal.text = data.attributes?.kasusMeni.toString()
        }
    }

}