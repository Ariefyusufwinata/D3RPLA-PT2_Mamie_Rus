package d3ifcool.bisapetcah.mamierus.presenter.ui.costumer

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.FrameLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import d3ifcool.bisapetcah.mamierus.R
import d3ifcool.bisapetcah.mamierus.core.datastore.PreferencesData
import d3ifcool.bisapetcah.mamierus.core.helper.Constant
import d3ifcool.bisapetcah.mamierus.core.helper.FAILURE_POST
import d3ifcool.bisapetcah.mamierus.core.helper.FAILURE_PRESENTER
import d3ifcool.bisapetcah.mamierus.core.helper.NOTIF
import d3ifcool.bisapetcah.mamierus.databinding.ActivityKonsumenCommentBinding
import d3ifcool.bisapetcah.mamierus.presenter.viewmodel.costumer.CommentViewModel

class CommentActivityK : AppCompatActivity() {

    private lateinit var binding: ActivityKonsumenCommentBinding
    private lateinit var model: CommentViewModel
    private lateinit var session: PreferencesData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKonsumenCommentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val title = resources.getString(R.string.comment)
        val actionBar = supportActionBar
        actionBar?.title = title
        actionBar?.setDisplayHomeAsUpEnabled(true)

        val id = intent.getIntExtra(Constant.EXTRA_ID, 0)

        session = PreferencesData(this@CommentActivityK)
        val token = session.getString(Constant.PREFS_TOKEN).toString()

        model = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[CommentViewModel::class.java]
        model.getOneProduct(id)
        model.getValue().observe(this) {
            when (it != null) {
                true -> {
                    val image = it.data?.images?.get(1)?.path

                    binding.apply {
                        Glide.with(this@CommentActivityK)
                            .load(image)
                            .centerCrop()
                            .into(imgPhoto)
                    }
                }
                false -> {
                    Toast.makeText(this@CommentActivityK, FAILURE_PRESENTER, Toast.LENGTH_LONG)
                        .show()
                }
            }
        }

        binding.apply {
            btnSend.setOnClickListener {
                val message = edtComment.text.toString()
                val rating = ratingBar.rating

                when {
                    message.isEmpty() -> {
                        alert("Mohon masukkan komentar anda!")
                        edtComment.requestFocus()
                    }
                    rating < 0 -> {
                        alert("Anda belum memberikan rating!")
                    }
                    else -> {
                        object : Thread() {
                            override fun run() {
                                try {
                                    model.sendComment(token, id, message, rating)
                                    alert("Terimakasih atas feedbacknya!")
                                    sleep(NOTIF)
                                    startActivity(
                                        Intent(
                                            this@CommentActivityK,
                                            MainActivityK::class.java
                                        )
                                    )
                                } catch (e: Exception) {
                                    Log.i(FAILURE_POST, "Failed! ${e.localizedMessage}")
                                }
                            }
                        }.start()
                    }
                }
            }
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menu?.add(Menu.NONE, 1, Menu.NONE, "Alamat Warung")
        menu?.add(Menu.NONE, 2, Menu.NONE, "Pengaturan")
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            1 -> startActivity(Intent(this, AddressActivityK::class.java))
            2 -> startActivity(Intent(this, SettingsActivityK::class.java))
        }
        return super.onOptionsItemSelected(item)
    }

    private fun alert(str: String) {
        val snack: Snackbar = Snackbar.make(
            binding.root,
            str,
            Snackbar.LENGTH_LONG
        )
        val view: View = snack.view
        val params = view.layoutParams as FrameLayout.LayoutParams
        params.gravity = Gravity.TOP
        view.layoutParams = params
        snack.show()
    }
}