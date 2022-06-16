package d3ifcool.bisapetcah.mamierus.presenter.viewmodel.auth

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import d3ifcool.bisapetcah.mamierus.core.connection.Client
import d3ifcool.bisapetcah.mamierus.core.helper.FAILURE_PRESENTER
import d3ifcool.bisapetcah.mamierus.core.helper.INTERNAL_SERVER
import d3ifcool.bisapetcah.mamierus.core.model.auth.GetProfileResponses
import d3ifcool.bisapetcah.mamierus.core.model.auth.LogoutResponses
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SettingsViewModel : ViewModel() {

    private val dataLogout = MutableLiveData<LogoutResponses>()

    private val dataProfile = MutableLiveData<GetProfileResponses>()
    fun getValue(): LiveData<GetProfileResponses> {
        return dataProfile
    }

    fun getProfile(token: String) {
        Client.instance.getProfile(
            token
        ).enqueue(object : Callback<GetProfileResponses> {
            override fun onResponse(
                call: Call<GetProfileResponses>,
                response: Response<GetProfileResponses>
            ) {
                if (response.isSuccessful) {
                    dataProfile.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<GetProfileResponses>, t: Throwable) {
                Log.e(INTERNAL_SERVER, t.message.toString())
            }

        })
    }

    fun logoutUser(token: String) {
        Client.instance.logout(
            token
        ).enqueue(object : Callback<LogoutResponses> {
            override fun onResponse(
                call: Call<LogoutResponses>,
                response: Response<LogoutResponses>
            ) {
                if (response.isSuccessful) {
                    dataLogout.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<LogoutResponses>, t: Throwable) {
                Log.e(FAILURE_PRESENTER, t.message.toString())
            }

        })
    }
}