package d3ifcool.bisapetcah.mamierus.adapter

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import d3ifcool.bisapetcah.mamierus.core.model.PublicGetProductResponses
import d3ifcool.bisapetcah.mamierus.databinding.RvFoodBinding

class PublicGetMenuAdapter (private val list : ArrayList<PublicGetProductResponses>) : RecyclerView.Adapter<PublicGetMenuAdapter.PublicGetMenuAdapterViewHolder>(){
    inner class PublicGetMenuAdapterViewHolder(private val binding : RvFoodBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(responses: PublicGetProductResponses) {
            binding.apply {
                val bitmap : Bitmap = BitmapFactory.decodeStream(responses.data?.path?.byteInputStream())
                imgMenu.setImageBitmap(bitmap)

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