package d3ifcool.bisapetcah.mamierus.presenter.viewmodel.auth

import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import d3ifcool.bisapetcah.mamierus.core.connection.Client
import d3ifcool.bisapetcah.mamierus.core.model.auth.LoginResponses
import d3ifcool.bisapetcah.mamierus.core.model.auth.ResetPasswordResponses
import d3ifcool.bisapetcah.mamierus.presenter.ui.auth.LoginActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ResetPasswordViewModel : ViewModel() {

    private val data = MutableLiveData<ResetPasswordResponses>()

    fun getValue() : LiveData<ResetPasswordResponses> {
        return data
    }

    fun forgetPassword(username: String, password: String) {
        Client.instance.reset(
            username,
            password
        ).enqueue(object : Callback<ResetPasswordResponses> {
            override fun onResponse(
                call: Call<ResetPasswordResponses>,
                response: Response<ResetPasswordResponses>
            ) {
                if(response.isSuccessful) {
                    data.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<ResetPasswordResponses>, t: Throwable) {
                Log.e("Fail Internal Error", t.message.toString())
            }

        })
    }
}