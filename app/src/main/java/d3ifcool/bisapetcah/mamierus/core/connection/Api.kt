package d3ifcool.bisapetcah.mamierus.core.connection

import d3ifcool.bisapetcah.mamierus.core.model.auth.LoginResponses
import d3ifcool.bisapetcah.mamierus.core.model.auth.LogoutResponses
import d3ifcool.bisapetcah.mamierus.core.model.auth.ResetPasswordResponses
import d3ifcool.bisapetcah.mamierus.core.model.konsumen.KonsumenRegisterResponses
import d3ifcool.bisapetcah.mamierus.core.model.pemilik.PemilikGetProfileResponses
import d3ifcool.bisapetcah.mamierus.core.model.publik.*
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
    ) : Call<KonsumenRegisterResponses>

    @FormUrlEncoded
    @POST("reset")
    fun reset(
        @Field("username") username : String,
        @Field("new_password") newPasssword : String,
    )  : Call<ResetPasswordResponses>

    @FormUrlEncoded
    @DELETE("logout")
    @Headers("Auth")
    fun logout(
    )  : Call<LogoutResponses>

    @GET("user")
    @Headers("Bearer Token : {token}")
    fun getPemilikProfile(
        @Header("token") token: String
    ) : Call<PemilikGetProfileResponses>

    //Publik
    @GET("public/product")
    fun getPublicProduct(
    ) : Call<PublicGetProductResponses>

    @GET("public/product")
    fun getPublicProductSearch(
        @Query("name") name : String
    ) : Call<PublicGetProductResponses>

    @GET("public/product?sortRating=DESC")
    fun getPublicProductSortMost(
    ) : Call<PublicGetProductResponses>

    @GET("public/product?sortPrice=DESC")
    fun getPublicProductSortPriceDESC(
    ) : Call<PublicGetProductResponses>

    @GET("public/product?sortPrice=ASC")
    fun getPublicProductSortPriceASC(
    ) : Call<PublicGetProductResponses>

    @GET("public/product/{product_id}")
    fun getPublicOneProduct(
        @Path("product_id") productId : Int
    ) : Call<PublicGetProductOneResponses>

    @GET("public/profile/{profile_id}")
    fun getPublicProfile(
        @Path("profile_id") profileId: Int,
    ) : Call<PublicGetProfileResponses>
}