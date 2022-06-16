package d3ifcool.bisapetcah.mamierus.presenter.viewmodel.general

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import d3ifcool.bisapetcah.mamierus.core.helper.INTERNAL_SERVER
import d3ifcool.bisapetcah.mamierus.core.connection.Client
import d3ifcool.bisapetcah.mamierus.core.model.general.PublicGetProfileResponses
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddressViewModel : ViewModel() {

    private val data = MutableLiveData<PublicGetProfileResponses>()

    fun getValue() : LiveData<PublicGetProfileResponses> {
        return data
    }

    fun getAddress() {
        Client.instance.getPublicProfile(
            1
        ).enqueue(object : Callback<PublicGetProfileResponses> {
            override fun onResponse(
                call: Call<PublicGetProfileResponses>,
                response: Response<PublicGetProfileResponses>
            )
            {
                if (response.isSuccessful) {
                    data.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<PublicGetProfileResponses>, t: Throwable) {
                Log.d(INTERNAL_SERVER, t.toString())
            }

        })
    }
}