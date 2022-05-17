package d3ifcool.bisapetcah.mamierus.adapter

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import d3ifcool.bisapetcah.mamierus.core.model.DataItem
import d3ifcool.bisapetcah.mamierus.core.model.PublicGetProductResponses
import d3ifcool.bisapetcah.mamierus.databinding.RvFoodBinding

class PublicGetMenuAdapter (private val list : List<DataItem?>) : RecyclerView.Adapter<PublicGetMenuAdapter.PublicGetMenuAdapterViewHolder>(){

//    private var onItemClickCallback : OnItemClickCallBack? = null
//
//    interface OnItemClickCallBack {
//        fun onItemClickCallBack
//    }

    inner class PublicGetMenuAdapterViewHolder(private val binding : RvFoodBinding) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(responses : DataItem?) {
            binding.apply {
                val nameMenu = responses?.name.toString()
                val priceMenu = responses?.price.toString()
                val imageMenu = responses?.images

                Log.i("image", imageMenu.toString())

                Glide.with(imgMenu)
                    .load(imageMenu)
                    .centerCrop()
                    .into(imgMenu)

                tvMenu.text = nameMenu
                tvHarga.text = "Rp. $priceMenu"

//                root.setOnClickListener {
//                    onItemClickCallback?.
//                }
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PublicGetMenuAdapterViewHolder {
        val view = RvFoodBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PublicGetMenuAdapterViewHolder(view)
    }

    override fun onBindViewHolder(holder: PublicGetMenuAdapterViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size
}