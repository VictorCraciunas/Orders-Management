package Model;

public class Bill {
    public Integer id;
    public String nameClient;
    public String nameProduct;
    public Integer quantity;
    public Integer totalPrice;

    public Bill() {
    }

    public Bill(Integer id, String nameClient, String nameProduct, Integer quantity, Integer totalPrice) {
        this.id = id;
        this.nameClient = nameClient;
        this.nameProduct = nameProduct;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

    public Bill(String nameClient, String nameProduct, Integer quantity, Integer totalPrice) {
        this.nameClient = nameClient;
        this.nameProduct = nameProduct;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

    public Integer getId() {
        return id;
    }

    public String getNameClient() {
        return nameClient;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNameClient(String nameClient) {
        this.nameClient = nameClient;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }
}
