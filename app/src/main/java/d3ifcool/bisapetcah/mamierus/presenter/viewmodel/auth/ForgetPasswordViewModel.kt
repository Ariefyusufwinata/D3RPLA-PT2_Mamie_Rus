package d3ifcool.bisapetcah.mamierus.presenter.viewmodel.auth


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import d3ifcool.bisapetcah.mamierus.core.connection.Client
import d3ifcool.bisapetcah.mamierus.core.helper.FAILURE_PRESENTER
import d3ifcool.bisapetcah.mamierus.core.model.auth.ResetPasswordResponses
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ForgetPasswordViewModel : ViewModel() {

    private val data = MutableLiveData<ResetPasswordResponses>()

    fun getValue() : LiveData<ResetPasswordResponses> {
        return data
    }

    fun forgetPassword(username: String, password: String) {
        Client.instance.reset(
            username,
            password
        ).enqueue(object : Callback<ResetPasswordResponses> {
            override fun onResponse(
                call: Call<ResetPasswordResponses>,
                response: Response<ResetPasswordResponses>
            ) {
                if(response.isSuccessful) {
                    data.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<ResetPasswordResponses>, t: Throwable) {
                Log.e(FAILURE_PRESENTER, t.message.toString())
            }

        })
    }
}