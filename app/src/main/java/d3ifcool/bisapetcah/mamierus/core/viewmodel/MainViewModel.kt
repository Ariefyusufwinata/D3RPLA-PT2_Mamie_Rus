package d3ifcool.bisapetcah.mamierus.core.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import d3ifcool.bisapetcah.mamierus.core.connection.Client
import d3ifcool.bisapetcah.mamierus.core.model.PublicGetProductResponses
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {
    val list = MutableLiveData<ArrayList<PublicGetProductResponses>>()

    fun getMenu() {
        Client.instance.getPublicProduct().enqueue(object :
            Callback<PublicGetProductResponses> {
            override fun onResponse(
                call: Call<PublicGetProductResponses>,
                response: Response<PublicGetProductResponses>
            ) {
                if (response.isSuccessful){
//                    list.postValue(response.body()?.data?.path.toString())
                }
            }

            override fun onFailure(call: Call<PublicGetProductResponses>, t: Throwable) {
                Log.e("Fail Error Internal", t.toString())
            }
        })
    }
}