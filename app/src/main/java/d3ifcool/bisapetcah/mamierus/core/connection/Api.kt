package d3ifcool.bisapetcah.mamierus.core.connection

import d3ifcool.bisapetcah.mamierus.core.model.auth.*
import d3ifcool.bisapetcah.mamierus.core.model.costumer.*
import d3ifcool.bisapetcah.mamierus.core.model.general.*
import retrofit2.http.*
import retrofit2.Call

interface Api {

    //Auth
    @FormUrlEncoded
    @POST("login")
    fun login(
        @Field("username") username : String,
        @Field("password") passsword : String,
    )  : Call<LoginResponses>

    @FormUrlEncoded
    @POST("register2") // Costumer
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

    @DELETE("logout")
    fun logout(
        @Header("Authorization") token: String
    )  : Call<LogoutResponses>

    @GET("user")
    fun getProfile(
        @Header("Authorization") token: String
    ) : Call<GetProfileResponses>

    @FormUrlEncoded
    @PUT("user")
    fun updateProfile(
        @Header("Authorization") token: String,
        @Field("password") password : String,
        @Field("phone") phone : String
    )  : Call<UpdateProfileResponses>

    //Costumer
    @FormUrlEncoded
    @POST("comment/create")
    fun postComment(
        @Header("Authorization") token: String,
        @Field("product_id") productId : Int,
        @Field("comment") comment : String,
        @Field("rating") rating : Float
    )  : Call<KonsumenKomentarResponses>

    //Public
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