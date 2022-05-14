package d3ifcool.bisapetcah.mamierus.core.model

data class PublicGetProductResponses(
	val code: Int? = null,
	val data: DataOneP? = null,
	val message: String? = null,
	val status: String? = null
)

data class CategoriesItemOne(
	val name: String? = null,
	val id: Int? = null
)

data class LinksItem(
	val active: Boolean? = null,
	val label: String? = null,
	val url: Any? = null
)

data class DataOneP(
	val perPage: Int? = null,
	val data: List<DataItem?>? = null,
	val lastPage: Int? = null,
	val nextPageUrl: Any? = null,
	val prevPageUrl: Any? = null,
	val firstPageUrl: String? = null,
	val path: String? = null,
	val total: Int? = null,
	val lastPageUrl: String? = null,
	val from: Int? = null,
	val links: List<LinksItem?>? = null,
	val to: Int? = null,
	val currentPage: Int? = null
)

data class ImagesItem(
	val path: String? = null,
	val size: String? = null,
	val updatedAt: String? = null,
	val name: String? = null,
	val createdAt: String? = null,
	val id: Int? = null
)

data class DataItem(
	val images: List<ImagesItem?>? = null,
	val description: String? = null,
	val createdAt: String? = null,
	val deletedAt: Any? = null,
	val updatedAt: String? = null,
	val chat: String? = null,
	val price: String? = null,
	val name: String? = null,
	val toppings: List<Any?>? = null,
	val id: Int? = null,
	val categories: List<CategoriesItemOne?>? = null,
	val stock: String? = null,
	val isEmpty: String? = null
)

