package d3ifcool.bisapetcah.mamierus.ui.pemilik

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import d3ifcool.bisapetcah.mamierus.databinding.ActivityPemilikAddressBinding

class AddressActivityP : AppCompatActivity() {

    private lateinit var binding : ActivityPemilikAddressBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPemilikAddressBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}