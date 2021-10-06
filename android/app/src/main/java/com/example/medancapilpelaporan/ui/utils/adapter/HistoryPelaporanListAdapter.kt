package app.disdukcapilmdn.sibisaapp.ui.utils.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.medancapilpelaporan.data.source.remote.response.HistoryPelaporan
import com.example.medancapilpelaporan.databinding.ItemListHistoryBinding
import com.example.medancapilpelaporan.ui.utils.click_listener.HistoryClickListener

class HistoryPelaporanListAdapter : RecyclerView.Adapter<HistoryPelaporanListAdapter.ViewHolder>() {

    private val listPelaporan = ArrayList<HistoryPelaporan>()
    var listener: HistoryClickListener? = null

    inner class ViewHolder(private val binding: ItemListHistoryBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(historyPelaporan: HistoryPelaporan) {
            with(binding) {

                jenisLaporan.text = historyPelaporan.namaJenis
                nik.text = historyPelaporan.nik
                nama.text = historyPelaporan.nama
                waktuSubmit.text = historyPelaporan.waktuSubmit

                itemView.setOnClickListener {
                    listener?.onHistoryItemClick("view", historyPelaporan.idJenis, historyPelaporan.id)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemListHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listPelaporan[position])
    }

    override fun getItemCount(): Int = listPelaporan.size

    fun setList(history: List<HistoryPelaporan>){
        listPelaporan.clear()
        listPelaporan.addAll(history)
    }
}