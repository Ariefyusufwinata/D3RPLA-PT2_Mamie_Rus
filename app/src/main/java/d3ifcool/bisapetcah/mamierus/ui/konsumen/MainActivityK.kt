package d3ifcool.bisapetcah.mamierus.view.ui.konsumen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import d3ifcool.bisapetcah.mamierus.databinding.ActivityKonsumenMainBinding

class MainActivityK : AppCompatActivity() {

    private lateinit var binding : ActivityKonsumenMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKonsumenMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}