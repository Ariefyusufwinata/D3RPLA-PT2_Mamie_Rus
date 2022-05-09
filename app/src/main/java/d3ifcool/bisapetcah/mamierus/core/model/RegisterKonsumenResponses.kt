package d3ifcool.bisapetcah.mamierus.core.model

import com.google.gson.annotations.SerializedName

data class RegisterKonsumenResponses(
    val code: Int? = null,
    val data: DataRegisterKonsumen? = null,
    val message: String? = null,
    val status: String? = null
)

data class DataRegisterKonsumen(
    @SerializedName("token")
    val accessToken: String? = null,
    val user: UserRegisterKonsumen? = null
)

data class UserRegisterKonsumen(
    val role: String? = null,
    @SerializedName("update_at")
    val updatedAt: String? = null,
    val name: String? = null,
    @SerializedName("created_at")
    val createdAt: String? = null,
    val id: Int? = null,
    val username: String? = null
)