package Model;

/**
 * Represents a bill for a transaction, containing details about the client, product, quantity, and total price.
 */
public class Bill {
    public Integer id;
    public String nameClient;
    public String nameProduct;
    public Integer quantity;
    public Integer totalPrice;

    /**
     * Default constructor for creating an empty Bill.
     */
    public Bill() {
    }

    /**
     * Constructs a Bill with all properties initialized.
     * @param id the unique identifier of the bill.
     * @param nameClient the name of the client associated with this bill.
     * @param nameProduct the name of the product being billed.
     * @param quantity the quantity of the product.
     * @param totalPrice the total price of the bill.
     */
    public Bill(Integer id, String nameClient, String nameProduct, Integer quantity, Integer totalPrice) {
        this.id = id;
        this.nameClient = nameClient;
        this.nameProduct = nameProduct;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

    /**
     * Constructs a Bill without an ID, typically used before persistence.
     * @param nameClient the name of the client associated with this bill.
     * @param nameProduct the name of the product being billed.
     * @param quantity the quantity of the product.
     * @param totalPrice the total price of the bill.
     */
    public Bill(String nameClient, String nameProduct, Integer quantity, Integer totalPrice) {
        this.nameClient = nameClient;
        this.nameProduct = nameProduct;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

    // Getter and setter methods with JavaDoc

    /**
     * Returns the ID of the bill.
     * @return the ID of the bill.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the ID of the bill.
     * @param id the ID to set.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Returns the client's name on the bill.
     * @return the client's name.
     */
    public String getNameClient() {
        return nameClient;
    }

    /**
     * Sets the client's name on the bill.
     * @param nameClient the client's name to set.
     */
    public void setNameClient(String nameClient) {
        this.nameClient = nameClient;
    }

    /**
     * Returns the product name on the bill.
     * @return the product name.
     */
    public String getNameProduct() {
        return nameProduct;
    }

    /**
     * Sets the product name on the bill.
     * @param nameProduct the product name to set.
     */
    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    /**
     * Returns the quantity of the product on the bill.
     * @return the quantity.
     */
    public Integer getQuantity() {
        return quantity;
    }

    /**
     * Sets the quantity of the product on the bill.
     * @param quantity the quantity to set.
     */
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    /**
     * Returns the total price of the bill.
     * @return the total price.
     */
    public Integer getTotalPrice() {
        return totalPrice;
    }

    /**
     * Sets the total price of the bill.
     * @param totalPrice the total price to set.
     */
    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }
}
