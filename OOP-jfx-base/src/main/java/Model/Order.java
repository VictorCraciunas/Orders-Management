package Model;

public class Order {
    public Integer id;
    public Integer clientId;
    public Integer productId;
    public Integer productQuantity;


    public Order() {
    }

    public Order(Integer id, Integer clientId, Integer productId, Integer quantity) {
        this.id = id;
        this.clientId = clientId;
        this.productId = productId;
        this.productQuantity = quantity;
    }

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

