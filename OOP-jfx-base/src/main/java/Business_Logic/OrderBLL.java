package Business_Logic;

import Model.Order;
import Model.Product;


public class OrderBLL extends AbstractBll<Order>{

    private ProductBLL productBLL=new ProductBLL();
    private BillBLL billBLL=new BillBLL();

    @Override
    public boolean isValidElement(Order order) {
        Product product=productBLL.abstractDAO.findById(order.getProductId());
        if(order.getProductQuantity() > product.getQuantity()) {
            return false;
        }
        updateProductQuantity(product,order.getProductQuantity());
        billBLL.addElement(billBLL.createBill(order.getClientId(),order.productId,order.getProductQuantity(),product.getPrice()*order.getProductQuantity()));
        return true;
    }

    public void updateProductQuantity(Product product, Integer orderQuantity){
        product.setQuantity(product.getQuantity() - orderQuantity);
        productBLL.updateElement(product);
    }
}
