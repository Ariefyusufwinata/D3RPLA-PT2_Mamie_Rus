package d3ifcool.bisapetcah.mamierus.core.model.general

import com.google.gson.annotations.SerializedName

data class PublicGetProductOneResponses(

	@field:SerializedName("code")
	val code: Int? = null,

	@field:SerializedName("data")
	val data: Data? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: String? = null
)

data class ImagesItemOne(

	@field:SerializedName("path")
	val path: String? = null,

	@field:SerializedName("size")
	val size: String? = null,

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("id")
	val id: Int? = null
)

data class CategoriesItemOne(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null
)

data class Data(

	@field:SerializedName("images")
	val images: List<ImagesItemOne?>? = null,

	@field:SerializedName("rating")
	val rating: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("deleted_at")
	val deletedAt: Any? = null,

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("chat")
	val chat: String? = null,

	@field:SerializedName("price")
	val price: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("toppings")
	val toppings: List<Any?>? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("categories")
	val categories: List<CategoriesItemOne?>? = null,

	@field:SerializedName("stock")
	val stock: String? = null,

	@field:SerializedName("user")
	val user: UserOne? = null,

	@field:SerializedName("is_empty")
	val isEmpty: String? = null
)

data class Profile(

	@field:SerializedName("address")
	val address: String? = null,

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("phone")
	val phone: String? = null,

	@field:SerializedName("is_open")
	val isOpen: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("map_url")
	val mapUrl: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("id")
	val id: Int? = null
)

data class UserOne(

	@field:SerializedName("role")
	val role: String? = null,

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("profile")
	val profile: Profile? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("id")
	val id: Int? = null
)
