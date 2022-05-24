package d3ifcool.bisapetcah.mamierus.presenter.util.notify

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import d3ifcool.bisapetcah.mamierus.databinding.ActivityNotificationSuccesAddBinding

class SuccesAddNotificationActivity : AppCompatActivity() {
    
    private lateinit var binding : ActivityNotificationSuccesAddBinding
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNotificationSuccesAddBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}