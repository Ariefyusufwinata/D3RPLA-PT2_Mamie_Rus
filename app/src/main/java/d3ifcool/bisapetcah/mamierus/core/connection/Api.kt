package d3ifcool.bisapetcah.mamierus.core.connection

import d3ifcool.bisapetcah.mamierus.core.model.*
import retrofit2.Call
import retrofit2.http.*

interface Api {

    //Auth
    @FormUrlEncoded
    @POST("login")
    fun login(
        @Field("username") username : String,
        @Field("password") passsword : String,
    )  : Call<LoginResponses>

    @FormUrlEncoded
    @POST("register2")
    fun register(
        @Field("username") username : String,
        @Field("password") password : String,
        @Field("phone") phone : String
    ) : Call<RegisterKonsumenResponses>

    @FormUrlEncoded
    @POST("reset")
    fun reset(
        @Field("username") username : String,
        @Field("new_password") newPasssword : String,
    )  : Call<ResetPasswordResponses>

    @FormUrlEncoded
    @DELETE("logout")
    @Headers("token")
    fun logout(
    )  : Call<LogoutUserResponses>

    //Konsumen



    //Pemilik

    //Publik
    @GET("public/product")
    fun getPublicProduct(
    ) : Call<PublicGetProductResponses>
}