package d3ifcool.bisapetcah.mamierus.presenter.ui.konsumen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import d3ifcool.bisapetcah.mamierus.databinding.ActivityKonsumenProfileBinding

class ProfileActivityK : AppCompatActivity() {

    private lateinit var binding : ActivityKonsumenProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKonsumenProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}