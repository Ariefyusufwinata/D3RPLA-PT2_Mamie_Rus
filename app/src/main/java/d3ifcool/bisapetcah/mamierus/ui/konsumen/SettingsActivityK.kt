package d3ifcool.bisapetcah.mamierus.ui.konsumen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import d3ifcool.bisapetcah.mamierus.databinding.ActivityKonsumenSettingsBinding

class SettingsActivityK : AppCompatActivity() {

    private lateinit var binding : ActivityKonsumenSettingsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKonsumenSettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}