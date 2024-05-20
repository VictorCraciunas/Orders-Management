package Business_Logic;

import Model.Order;
import Model.Product;

import java.util.Objects;


public class ProductBLL extends AbstractBll<Product>{


    @Override
    public boolean isValidElement(Product product) {
        return true;
    }
    @Override
    protected int getGeneratedId() {
        return abstractDAO.getLastInsertId(); // Implement this method in your AbstractDAO class to fetch the last inserted ID
    }
}
