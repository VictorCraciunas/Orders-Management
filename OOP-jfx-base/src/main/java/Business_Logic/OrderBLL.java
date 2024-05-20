package Business_Logic;

import Model.Order;
import Model.Product;


public class OrderBLL extends AbstractBll<Order>{

    private ProductBLL productBLL=new ProductBLL();

    @Override
    public boolean isValidElement(Order order) {
        Product product=productBLL.abstractDAO.findById(order.getProductId());
        if(order.getProductQuantity() > product.getQuantity()) {
            return false;
        }
        updateProductQuantity(product,order.getProductQuantity());
        return true;
    }

    public void updateProductQuantity(Product product, Integer orderQuantity){
        product.setQuantity(product.getQuantity() - orderQuantity);
        productBLL.updateElement(product);
    }
    @Override
    protected int getGeneratedId() {
        return abstractDAO.getLastInsertId(); // Implement this method in your AbstractDAO class to fetch the last inserted ID
    }
}
