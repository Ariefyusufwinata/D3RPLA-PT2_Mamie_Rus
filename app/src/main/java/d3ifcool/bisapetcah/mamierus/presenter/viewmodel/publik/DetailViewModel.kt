package d3ifcool.bisapetcah.mamierus.presenter.viewmodel.publik

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import d3ifcool.bisapetcah.mamierus.core.helper.INTERNAL_SERVER
import d3ifcool.bisapetcah.mamierus.core.connection.Client
import d3ifcool.bisapetcah.mamierus.core.model.publik.PublicGetProductOneResponses
import d3ifcool.bisapetcah.mamierus.core.model.publik.PublicGetProfileResponses
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel : ViewModel() {

    private val data = MutableLiveData<PublicGetProductOneResponses>()

    fun getValue() : LiveData<PublicGetProductOneResponses> {
        return data
    }

    fun getDetailProduct(id : Int) {
        Client.instance.getPublicOneProduct(
            id
        ).enqueue(object : Callback<PublicGetProductOneResponses>{
            override fun onResponse(
                call: Call<PublicGetProductOneResponses>,
                response: Response<PublicGetProductOneResponses>
            ) {
                if(response.isSuccessful) {
                    data.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<PublicGetProductOneResponses>, t: Throwable) {
                Log.e(INTERNAL_SERVER, t.message.toString())
            }
        })
    }

}