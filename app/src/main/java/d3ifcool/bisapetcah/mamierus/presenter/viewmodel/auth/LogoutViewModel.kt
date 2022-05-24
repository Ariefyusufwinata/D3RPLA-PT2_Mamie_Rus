package d3ifcool.bisapetcah.mamierus.presenter.viewmodel.auth

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import d3ifcool.bisapetcah.mamierus.core.connection.Client
import d3ifcool.bisapetcah.mamierus.core.model.auth.LogoutResponses
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LogoutViewModel : ViewModel() {

    private val data = MutableLiveData<LogoutResponses>()

    fun getValue() : LiveData<LogoutResponses> {
        return data
    }

    fun logoutUser() {
        Client.instance.logout(
        ).enqueue(object : Callback<LogoutResponses> {
            override fun onResponse(
                call: Call<LogoutResponses>,
                response: Response<LogoutResponses>
            ) {
                if(response.isSuccessful) {
                    data.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<LogoutResponses>, t: Throwable) {
                Log.e("Fail Internal Error", t.message.toString())
            }

        })
    }
}