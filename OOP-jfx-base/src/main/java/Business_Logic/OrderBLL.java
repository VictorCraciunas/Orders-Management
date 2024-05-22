package Business_Logic;

import Model.Order;
import Model.Product;

/**
 * This class handles the business logic specific to Order objects.
 * It extends the generic AbstractBll class to include operations tailored for orders.
 */
public class OrderBLL extends AbstractBll<Order> {

    private final ProductBLL productBLL = new ProductBLL();
    private final BillBLL billBLL = new BillBLL();

    /**
     * Validates an Order object by checking product availability against the requested quantity.
     * Updates product quantity and generates a bill if the order is valid.
     *
     * @param order The order to validate.
     * @return true if the order can be fulfilled (enough stock and bill generated), false otherwise.
     */
    @Override
    public boolean isValidElement(Order order) {
        Product product = productBLL.abstractDAO.findById(order.getProductId());
        if (order.getProductQuantity() > product.getQuantity()) {
            return false;
        }
        updateProductQuantity(product, order.getProductQuantity());
        billBLL.addElement(billBLL.createBill(order.getClientId(), order.getProductId(), order.getProductQuantity(), product.getPrice() * order.getProductQuantity()));
        return true;
    }

    /**
     * Updates the quantity of a product based on an order.
     * Reduces the stock quantity by the ordered amount.
     *
     * @param product The product whose quantity needs to be updated.
     * @param orderQuantity The amount of the product that has been ordered.
     */
    public void updateProductQuantity(Product product, Integer orderQuantity) {
        product.setQuantity(product.getQuantity() - orderQuantity);
        productBLL.updateElement(product);
    }
}
