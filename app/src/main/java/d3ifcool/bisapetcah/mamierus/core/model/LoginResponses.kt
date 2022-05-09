package d3ifcool.bisapetcah.mamierus.core.model

import com.google.gson.annotations.SerializedName

data class LoginResponses(
    val code: Int? = null,
    val data: DataLogin? = null,
    val message: String? = null,
    val status: String? = null
)

data class UserLogin(
    val role: String? = null,
    @SerializedName("update_at")
    val updatedAt: String? = null,
    val name: String? = null,
    @SerializedName("created_at")
    val createdAt: String? = null,
    val id: Int? = null,
    val username: String? = null
)

data class DataLogin(
    @SerializedName("access_token")
    val token: String? = null,
    val user: UserLogin? = null
)