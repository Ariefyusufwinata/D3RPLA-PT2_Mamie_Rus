package d3ifcool.bisapetcah.mamierus.core.model

data class PublicGetOneProductResponses(
	val code: Int? = null,
	val data: Data? = null,
	val message: String? = null,
	val status: String? = null
)

data class Data(
	val images: List<ImagesItemOneP?>? = null,
	val description: String? = null,
	val createdAt: String? = null,
	val deletedAt: Any? = null,
	val updatedAt: String? = null,
	val chat: String? = null,
	val price: String? = null,
	val name: String? = null,
	val toppings: List<Any?>? = null,
	val id: Int? = null,
	val categories: List<CategoriesItem?>? = null,
	val stock: String? = null,
	val isEmpty: String? = null
)

data class ImagesItemOneP(
	val path: String? = null,
	val size: String? = null,
	val updatedAt: String? = null,
	val name: String? = null,
	val createdAt: String? = null,
	val id: Int? = null
)

data class CategoriesItem(
	val name: String? = null,
	val id: Int? = null
)

