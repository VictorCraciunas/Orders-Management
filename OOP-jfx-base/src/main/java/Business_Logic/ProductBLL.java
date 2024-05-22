package Business_Logic;

import Model.Order;
import Model.Product;

import java.util.Objects;


public class ProductBLL extends AbstractBll<Product>{


    @Override
    public boolean isValidElement(Product product) {
        if(product.getQuantity() >=0 && product.getPrice() >=0){
            return true;
        }
        return false;
    }

}
