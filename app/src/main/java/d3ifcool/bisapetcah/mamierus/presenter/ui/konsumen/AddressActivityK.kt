package d3ifcool.bisapetcah.mamierus.presenter.ui.konsumen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import d3ifcool.bisapetcah.mamierus.databinding.ActivityKonsumenAddressBinding

class AddressActivityK : AppCompatActivity() {

    private lateinit var binding : ActivityKonsumenAddressBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKonsumenAddressBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}