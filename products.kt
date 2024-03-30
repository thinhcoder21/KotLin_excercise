import java.util.*

enum class ProductCategory {
    LAPTOP,
    PHONE,
    HEADPHONES,
    SMART_WATCH,
    CAMERA,
}

data class Product(
    val id: String,
    val name: String,
    val price: Double,
    val category: ProductCategory,
    val favoriteCount: Int,
)

data class Order(
    val id: String,
    val products: List<Product>,
    val isDelivered: Boolean,
)

// TODO: Return a list of Product, sorted in the ascending by price. if prices are equal, sorted by favoriteCount descending
fun List<Product>.sortedByPriceAscendingThenByFavoriteCountDescending(): List<Product> {
    return this.sortedWith(compareBy ({ it.price }, { -it.favoriteCount}))
}

// TODO: Return a set of Products in the orders (The order doesn't matter).
fun List<Order>.getProductsSet(): Set<Product> {
    val productSet = mutableSetOf<Product>()
    this.forEach{order -> productSet.addAll(order.products)}
    return productSet
}

// TODO: Return a list of Products in the orders, duplicates are allowed.
fun List<Order>.getProductsList(): List<Product> {
    val productList = mutableListOf<Product>()
    this.forEach{order: Order ->  productList.addAll(order.products)}
    return productList
}

// TODO: Return a list of delivered orders
fun List<Order>.getDeliveredOrders(): List<Order> {
    return this.filter { it.isDelivered == true }
    
}

// TODO: Return a list of products in the delivered orders
fun List<Order>.getDeliveredProductsList(): List<Product> {
    val deliveredOrders = this.filter { it.isDelivered }
    val deliveredProduct = mutableListOf<Product>()

    deliveredOrders.forEach {order -> deliveredProduct.addAll(order.products)}

    return deliveredProduct
}

// TODO: Partition the orders into two lists: "delivered" and "not delivered"
fun List<Order>.partitionDeliveredAndNotDelivered(): Pair<List<Order>, List<Order>> {
    return this.partition { it.isDelivered }
}

// TODO: Return a map of product to count of this product in the orders
// eg. [Product1 -> 2, Product2 -> 1, Product3 -> 3]
fun List<Order>.countOfEachProduct(): Map<Product, Int>  {
    val productCounts = mutableMapOf<Product,Int>()

    this.forEach{ order -> order.products.forEach{product -> productCounts[product]=productCounts.getOrDefault(product,0)+1}}
    return productCounts
}

// TODO: Return the sum of product prices in the order
fun Order.sumProductPrice(): Double {
    return this.products.sumByDouble { it.price }
}

// TODO: Return the product with the maximum price in the order
fun Order.getMaxPriceProduct(): Product {
    return this.products.maxBy({it.price})
}

// TODO: Return the product with the min price in the order
fun Order.getMinPriceProduct(): Product  {
    return this.products.minBy ( {it.price} )
}

val product = Product(
    id = UUID.randomUUID().toString(),
    name = "Sandy Short Special Edition",
    price = 2.3,
    category = ProductCategory.LAPTOP,
    favoriteCount = 1,
)

val productList = listOf(
    product,
    Product(
        id = UUID.randomUUID().toString(),
        name = "Stacie Riddle",
        price = 6.7,
        category = ProductCategory.PHONE,
        favoriteCount = 2,
    ),
    Product(
        id = UUID.randomUUID().toString(),
        name = "Stacie Riddle",
        price = 6.7,
        category = ProductCategory.LAPTOP,
        favoriteCount = 3,
    ),
    Product(
        id = UUID.randomUUID().toString(),
        name = "Stacie Riddle",
        price = 6.7,
        category = ProductCategory.SMART_WATCH,
        favoriteCount = 4,
    ),
    Product(
        id = UUID.randomUUID().toString(),
        name = "Stacie Riddle",
        price = 1.0,
        category = ProductCategory.HEADPHONES,
        favoriteCount = 5,
    ),
    Product(
        id = UUID.randomUUID().toString(),
        name = "Stacie Riddle",
        price = 10.0,
        category = ProductCategory.CAMERA,
        favoriteCount = 0,
    ),
)

val orderList = listOf(
    Order(
        id = UUID.randomUUID().toString(),
        products = listOf(
            product,
            Product(
                id = UUID.randomUUID().toString(),
                name = "Stacie Riddle",
                price = 6.7,
                category = ProductCategory.PHONE,
                favoriteCount = 2,
            ),
        ),
        isDelivered = true,
    ),
    Order(
        id = UUID.randomUUID().toString(),
        products = listOf(
            product,
            Product(
                id = UUID.randomUUID().toString(),
                name = "Stacie Riddle",
                price = 100.0,
                category = ProductCategory.SMART_WATCH,
                favoriteCount = 3,
            ),
        ),
        isDelivered = false,
    ),
    Order(
        id = UUID.randomUUID().toString(),
        products = listOf(
            product,
            Product(
                id = UUID.randomUUID().toString(),
                name = "Stacie Riddle",
                price = 6.7,
                category = ProductCategory.PHONE,
                favoriteCount = 2,
            ),
            Product(
                id = UUID.randomUUID().toString(),
                name = "Efrain Hawkins",
                price = 100.0,
                category = ProductCategory.CAMERA,
                favoriteCount = 5235,
            ),
        ),
        isDelivered = true,
    ),
)

fun main() {
    //region sortedByPriceAscendingThenByFavoriteCountDescending
    println("sortedByPriceAscendingThenByFavoriteCountDescending")
    println(productList.sortedByPriceAscendingThenByFavoriteCountDescending())
    //endregion

    //region getProductsSet
    println("getProductsSet")
    println(orderList.getProductsSet())
    //endregion

    //region getProductsList
    println("getProductsList")
    println(orderList.getProductsList())
    //endregion

    //region getDeliveredOrders
    println("getDeliveredOrders")
    println(orderList.getDeliveredOrders())
    //endregion getDeliveredProductsList

    //region getDeliveredProductsList
    println("getDeliveredProductsList")
    println(orderList.getDeliveredProductsList())
    //endregion

    //region partitionDeliveredAndNotDelivered
    println("partitionDeliveredAndNotDelivered")
    println(orderList.partitionDeliveredAndNotDelivered())
    //endregion

    //region countOfEachProduct
    println("countOfEachProduct")
    println(orderList.countOfEachProduct())
    //endregion

    //region sumProductPrice
    println("sumProductPrice")
    println(orderList[0].sumProductPrice())
    //endregion

    //region getMaxPriceProduct, getMinPriceProduct
    println("getMaxPriceProduct, getMinPriceProduct")
    println(orderList[0].getMaxPriceProduct())
    println(orderList[0].getMinPriceProduct())
    //endregion
}