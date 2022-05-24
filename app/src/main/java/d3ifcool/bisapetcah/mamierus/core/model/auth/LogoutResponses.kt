package d3ifcool.bisapetcah.mamierus.core.model.auth

import com.google.gson.annotations.SerializedName

data class LogoutResponses(

	@field:SerializedName("code")
	val code: Int? = null,

	@field:SerializedName("data")
	val data: Boolean? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: String? = null
)
