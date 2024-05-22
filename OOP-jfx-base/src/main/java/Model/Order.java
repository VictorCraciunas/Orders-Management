package Model;

/**
 * Represents an order within the system, detailing the client, product, and quantity ordered.
 */
public class Order {
    public Integer id;
    public Integer clientId;
    public Integer productId;
    public Integer productQuantity;


    /**
     * Default constructor for creating an empty Order.
     */
    public Order() {
    }

    /**
     * Constructs an Order with an ID, client ID, product ID, and the quantity of the product ordered.
     * This constructor is typically used when retrieving an order's information from the database.
     * @param id The unique identifier of the order.
     * @param clientId The client's ID associated with this order.
     * @param productId The product's ID being ordered.
     * @param quantity The quantity of the product ordered.
     */
    public Order(Integer id, Integer clientId, Integer productId, Integer quantity) {
        this.id = id;
        this.clientId = clientId;
        this.productId = productId;
        this.productQuantity = quantity;
    }

    /**
     * Constructs an Order without an ID, typically used before persisting a new order.
     * @param clientId The client's ID associated with this order.
     * @param productId The product's ID being ordered.
     * @param quantity The quantity of the product ordered.
     */
    public Order(Integer clientId, Integer productId, Integer quantity) {
        this.clientId = clientId;
        this.productId = productId;
        this.productQuantity = quantity;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public void setProductQuantity(Integer quantity) {
        this.productQuantity = quantity;
    }

    public Integer getId() {
        return id;
    }

    public Integer getClientId() {
        return clientId;
    }

    public Integer getProductId() {
        return productId;
    }

    public Integer getProductQuantity() {
        return productQuantity;
    }
}

