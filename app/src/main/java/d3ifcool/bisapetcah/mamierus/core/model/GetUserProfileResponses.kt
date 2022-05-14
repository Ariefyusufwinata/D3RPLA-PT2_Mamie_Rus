package d3ifcool.bisapetcah.mamierus.core.model

data class GetUserProfileResponses(
	val code: Int? = null,
	val data: DataUser? = null,
	val message: String? = null,
	val status: String? = null
)

data class DataUser(
	val role: String? = null,
	val updatedAt: String? = null,
	val profile: Profile? = null,
	val name: String? = null,
	val createdAt: String? = null,
	val id: Int? = null,
	val username: String? = null
)

data class Profile(
	val images: List<ImagesProfile?>? = null,
	val address: String? = null,
	val updatedAt: String? = null,
	val phone: String? = null,
	val isOpen: String? = null,
	val name: String? = null,
	val mapUrl: String? = null,
	val description: String? = null,
	val createdAt: String? = null,
	val id: Int? = null
)

data class ImagesProfile(
	val path: String? = null,
	val size: String? = null,
	val updatedAt: String? = null,
	val name: String? = null,
	val createdAt: String? = null,
	val id: Int? = null
)

