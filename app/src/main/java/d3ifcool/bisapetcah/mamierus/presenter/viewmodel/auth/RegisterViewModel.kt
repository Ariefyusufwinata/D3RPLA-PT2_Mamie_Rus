package d3ifcool.bisapetcah.mamierus.presenter.viewmodel.auth

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import d3ifcool.bisapetcah.mamierus.core.connection.Client
import d3ifcool.bisapetcah.mamierus.core.helper.FAILURE_PRESENTER
import d3ifcool.bisapetcah.mamierus.core.model.costumer.KonsumenRegisterResponses
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterViewModel : ViewModel() {

    private val data = MutableLiveData<KonsumenRegisterResponses>()

    fun getValue() : LiveData<KonsumenRegisterResponses> {
        return data
    }

    fun register(username : String, password : String, phone : String) {
        Client.instance.register(
            username,
            password,
            phone
        ).enqueue(object  : Callback<KonsumenRegisterResponses> {
            override fun onResponse(
                call: Call<KonsumenRegisterResponses>,
                response: Response<KonsumenRegisterResponses>
            ) {
                if(response.isSuccessful) {
                    data.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<KonsumenRegisterResponses>, t: Throwable) {
                Log.e(FAILURE_PRESENTER, t.message.toString())
            }

        })
    }
}