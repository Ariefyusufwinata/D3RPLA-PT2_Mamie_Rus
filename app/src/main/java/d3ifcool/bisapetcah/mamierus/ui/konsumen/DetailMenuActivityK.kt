package d3ifcool.bisapetcah.mamierus.ui.konsumen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import d3ifcool.bisapetcah.mamierus.databinding.ActivityKonsumenDetailMenuBinding

class DetailMenuActivityK : AppCompatActivity() {

    private lateinit var binding : ActivityKonsumenDetailMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKonsumenDetailMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}