package d3ifcool.bisapetcah.mamierus.presenter.adapter.konsumen

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import d3ifcool.bisapetcah.mamierus.core.model.publik.DataItem
import d3ifcool.bisapetcah.mamierus.databinding.RvFoodBinding

class MainKAdapter : RecyclerView.Adapter<MainKAdapter.KonsumenGetMenuAdapterViewHolder>() {

    private val dataMenu = mutableListOf<DataItem?>()
    private var onItemClickCallback: OnItemClickCallback? = null

    interface OnItemClickCallback{
        fun onItemClicked(data: DataItem?)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(menus : List<DataItem?>){
        dataMenu.clear()
        dataMenu.addAll(menus)
        notifyDataSetChanged()
    }

    inner class KonsumenGetMenuAdapterViewHolder(private val binding : RvFoodBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(responses : DataItem?) {
            binding.apply {
                val imageMenu = responses?.images?.get(0)?.path
                val nameMenu = responses?.name.toString()
                val priceMenu = responses?.price.toString()
                val rating = responses?.rating.toString()

                //Presenter
                Glide.with(itemView)
                    .load(imageMenu)
                    .centerCrop()
                    .into(imgMenu)
                tvMenu.text = nameMenu
                tvHarga.text = "Rp. $priceMenu"
                tvRating.text = rating

                root.setOnClickListener {
                    onItemClickCallback?.onItemClicked(responses)
                    Log.d("item", responses?.id.toString())
                }
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): KonsumenGetMenuAdapterViewHolder {
        val view = RvFoodBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return KonsumenGetMenuAdapterViewHolder(view)
    }

    override fun onBindViewHolder(holder: KonsumenGetMenuAdapterViewHolder, position: Int) {
        holder.bind(dataMenu[position])
    }

    override fun getItemCount(): Int = dataMenu.size
}