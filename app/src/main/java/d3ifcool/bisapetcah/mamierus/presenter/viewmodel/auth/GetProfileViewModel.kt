package d3ifcool.bisapetcah.mamierus.presenter.viewmodel.auth

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import d3ifcool.bisapetcah.mamierus.core.connection.Client
import d3ifcool.bisapetcah.mamierus.core.helper.INTERNAL_SERVER
import d3ifcool.bisapetcah.mamierus.core.model.auth.GetProfileResponses
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GetProfileViewModel : ViewModel() {

    private val data = MutableLiveData<GetProfileResponses>()

    fun getValue() : LiveData<GetProfileResponses> {
        return data
    }

    fun getProfile(token : String) {
        Client.instance.getProfile(
            token
        ).enqueue(object : Callback<GetProfileResponses>{
            override fun onResponse(
                call: Call<GetProfileResponses>,
                response: Response<GetProfileResponses>
            ) {
                if(response.isSuccessful) {
                    data.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<GetProfileResponses>, t: Throwable) {
                Log.e(INTERNAL_SERVER, t.message.toString())
            }

        })
    }
}