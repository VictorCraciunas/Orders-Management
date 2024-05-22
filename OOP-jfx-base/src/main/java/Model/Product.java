package Model;

/**
 * Represents a product with associated properties such as name, price, and quantity.
 */
public class Product {
    public Integer id;
    public String productName;
    public Integer price;
    public Integer quantity;

    /**
     * Constructs a Product with an ID, name, price, and quantity.
     * This constructor is typically used when retrieving a product's information from the database.
     * @param id The unique identifier of the product.
     * @param productName The name of the product.
     * @param price The price of the product.
     * @param quantity The stock quantity of the product.
     */
    public Product(Integer id, String productName, Integer price, Integer quantity) {
        this.id = id;
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
    }

    /**
     * Constructs a Product without an ID, typically used before persisting a new product.
     * @param productName The name of the product.
     * @param price The price of the product.
     * @param quantity The stock quantity of the product.
     */
    public Product(String productName, Integer price, Integer quantity) {
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
    }

    /**
     * Default constructor for creating an empty Product.
     */
    public Product() {
    }

    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Returns a string representation of the product, primarily the product name.
     * @return The product name as a string.
     */
    @Override
    public String toString() {
        return  productName ;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getId() {
        return id;
    }

    public String getProductName() {
        return productName;
    }

    public Integer getPrice() {
        return price;
    }

    public Integer getQuantity() {
        return quantity;
    }
}
