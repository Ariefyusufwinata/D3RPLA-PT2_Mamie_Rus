package d3ifcool.bisapetcah.mamierus.presenter.viewmodel.auth

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import d3ifcool.bisapetcah.mamierus.core.connection.Client
import d3ifcool.bisapetcah.mamierus.core.helper.INTERNAL_SERVER
import d3ifcool.bisapetcah.mamierus.core.model.auth.GetProfileResponses
import d3ifcool.bisapetcah.mamierus.core.model.auth.UpdateProfileResponses
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UpdateProfileViewModel : ViewModel() {

    private val dataUpdate = MutableLiveData<UpdateProfileResponses>()

    fun update(token : String, password : String, phone : String) {
        Client.instance.updateProfile(
            token,
            password,
            phone
        ).enqueue(object : Callback<UpdateProfileResponses> {
            override fun onResponse(
                call: Call<UpdateProfileResponses>,
                response: Response<UpdateProfileResponses>
            ) {
                if(response.isSuccessful) {
                    dataUpdate.postValue(response.body())
                }
            }
            override fun onFailure(call: Call<UpdateProfileResponses>, t: Throwable) {
                Log.e(INTERNAL_SERVER, t.message.toString())
            }
        })
    }

    private val dataProfile = MutableLiveData<GetProfileResponses>()

    fun getValue() : LiveData<GetProfileResponses> {
        return dataProfile
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
                    dataProfile.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<GetProfileResponses>, t: Throwable) {
                Log.e(INTERNAL_SERVER, t.message.toString())
            }

        })
    }
}
