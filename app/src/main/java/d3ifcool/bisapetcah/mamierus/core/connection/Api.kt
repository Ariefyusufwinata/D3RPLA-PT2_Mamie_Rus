package d3ifcool.bisapetcah.mamierus.core.connection

import d3ifcool.bisapetcah.mamierus.core.model.LoginResponses
import d3ifcool.bisapetcah.mamierus.core.model.RegisterKonsumenResponses
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface Api {

    @FormUrlEncoded
    @POST("register")
    fun register(
        @Field("name") name : String,
        @Field("username") username : String,
        @Field("password") password : String,
        @Field("phone") phone : String,
        @Field("address") address : String,
    ) : Call<RegisterKonsumenResponses>

    @FormUrlEncoded
    @POST("login")
    fun login(
        @Field("username") username : String,
        @Field("password") passsword : String,
    )  : Call<LoginResponses>
}