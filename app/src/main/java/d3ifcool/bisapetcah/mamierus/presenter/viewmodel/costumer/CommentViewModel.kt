package d3ifcool.bisapetcah.mamierus.presenter.viewmodel.costumer

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import d3ifcool.bisapetcah.mamierus.core.connection.Client
import d3ifcool.bisapetcah.mamierus.core.helper.INTERNAL_SERVER
import d3ifcool.bisapetcah.mamierus.core.model.costumer.KonsumenKomentarResponses
import d3ifcool.bisapetcah.mamierus.core.model.general.PublicGetProductOneResponses
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CommentViewModel : ViewModel() {

    private val dataProduct = MutableLiveData<PublicGetProductOneResponses>()

    private val dataComment = MutableLiveData<KonsumenKomentarResponses>()

    fun getValue() : LiveData<PublicGetProductOneResponses> {
        return dataProduct
    }

    fun getOneProduct(id : Int) {
        Client.instance.getPublicOneProduct(
            id
        ).enqueue(object : Callback<PublicGetProductOneResponses> {
            override fun onResponse(
                call: Call<PublicGetProductOneResponses>,
                response: Response<PublicGetProductOneResponses>
            ) {
                if(response.isSuccessful) {
                    dataProduct.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<PublicGetProductOneResponses>, t: Throwable) {
                Log.e(INTERNAL_SERVER, t.message.toString())
            }
        })
    }

    fun sendComment(token : String, id : Int, message : String, rating : Float) {
        Client.instance.postComment(
            token,
            id,
            message,
            rating
        ).enqueue(object : Callback<KonsumenKomentarResponses>{
            override fun onResponse(
                call: Call<KonsumenKomentarResponses>,
                response: Response<KonsumenKomentarResponses>
            ) {
                if(response.isSuccessful) {
                    dataComment.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<KonsumenKomentarResponses>, t: Throwable) {
                Log.e(INTERNAL_SERVER, t.message.toString())
            }

        })
    }
}