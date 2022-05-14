package d3ifcool.bisapetcah.mamierus.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import d3ifcool.bisapetcah.mamierus.core.model.PublicGetProductResponses
import d3ifcool.bisapetcah.mamierus.databinding.ItemFoodBinding

class PublicGetMenuAdapter (private val list : ArrayList<PublicGetProductResponses>) : RecyclerView.Adapter<PublicGetMenuAdapter.PublicGetMenuAdapterViewHolder>(){
    inner class PublicGetMenuAdapterViewHolder(private val binding : ItemFoodBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(responses: PublicGetProductResponses) {

        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PublicGetMenuAdapterViewHolder {
        val view = ItemFoodBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PublicGetMenuAdapterViewHolder(view)
    }

    override fun onBindViewHolder(holder: PublicGetMenuAdapterViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size
}