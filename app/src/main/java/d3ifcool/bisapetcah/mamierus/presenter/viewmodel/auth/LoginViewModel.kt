package d3ifcool.bisapetcah.mamierus.presenter.viewmodel.auth

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import d3ifcool.bisapetcah.mamierus.core.helper.INTERNAL_SERVER
import d3ifcool.bisapetcah.mamierus.core.connection.Client
import d3ifcool.bisapetcah.mamierus.core.model.auth.LoginResponses
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel : ViewModel() {

    private val data = MutableLiveData<LoginResponses>()

    fun getValue() : LiveData<LoginResponses> {
        return data
    }

    fun loginUser(username : String, password : String) {
        Client.instance.login(
            username,
            password
        ).enqueue(object : Callback<LoginResponses> {
            override fun onResponse(
                call: Call<LoginResponses>,
                response: Response<LoginResponses>
            ) {
                if(response.isSuccessful) {
                    data.postValue(response.body())
                }
            }
            override fun onFailure(call: Call<LoginResponses>, t: Throwable) {
                Log.e(INTERNAL_SERVER, t.message.toString())
            }
        })
    }
}