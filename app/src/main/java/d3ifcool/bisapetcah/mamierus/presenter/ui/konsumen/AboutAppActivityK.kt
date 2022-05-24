package d3ifcool.bisapetcah.mamierus.presenter.ui.konsumen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import d3ifcool.bisapetcah.mamierus.databinding.ActivityKonsumenAboutAppBinding

class AboutAppActivityK : AppCompatActivity() {

    private lateinit var binding : ActivityKonsumenAboutAppBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKonsumenAboutAppBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}