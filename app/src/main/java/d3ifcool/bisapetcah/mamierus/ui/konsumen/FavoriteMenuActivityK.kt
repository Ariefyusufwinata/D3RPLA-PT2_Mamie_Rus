package d3ifcool.bisapetcah.mamierus.ui.konsumen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import d3ifcool.bisapetcah.mamierus.databinding.ActivityKonsumenFavoriteMenuBinding

class FavoriteMenuActivityK : AppCompatActivity() {

    private lateinit var binding : ActivityKonsumenFavoriteMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKonsumenFavoriteMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}