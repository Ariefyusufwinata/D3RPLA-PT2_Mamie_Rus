package d3ifcool.bisapetcah.mamierus.core.model.costumer

import com.google.gson.annotations.SerializedName

data class KonsumenKomentarResponses(

	@field:SerializedName("code")
	val code: Int? = null,

	@field:SerializedName("data")
	val data: DataMenu? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: String? = null
)

data class DataMenu(

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("rating")
	val rating: String? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("comment")
	val comment: String? = null,

	@field:SerializedName("id")
	val id: Int? = null
)
