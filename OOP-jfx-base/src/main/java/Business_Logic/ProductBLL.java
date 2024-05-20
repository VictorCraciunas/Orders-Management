package Business_Logic;

import Model.Order;
import Model.Product;

import java.util.Objects;


public class ProductBLL extends AbstractBll<Product>{


    @Override
    public boolean isValidElement(Product product) {
        return true;
    }

}
