package d3ifcool.bisapetcah.mamierus.view.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import d3ifcool.bisapetcah.mamierus.databinding.ActivityAddressBinding

class AddressActivity : AppCompatActivity() {

    private lateinit var binding : ActivityAddressBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddressBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}