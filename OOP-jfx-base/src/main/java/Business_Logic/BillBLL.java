package Business_Logic;

import Model.Bill;

/**
 * This class handles the business logic specific to Bill objects.
 * It extends the generic AbstractBll class to include operations tailored for bills.
 */
public class BillBLL extends AbstractBll<Bill> {
    ClientBLL clientBLL = new ClientBLL();
    ProductBLL productBLL = new ProductBLL();

    /**
     * Validates a Bill object. Currently, always returns true, assuming all bills are valid.
     * @param bill The bill to validate.
     * @return true, indicating the bill is valid.
     */
    @Override
    public boolean isValidElement(Bill bill) {
        return true;  // In a real scenario, you would implement validation logic here.
    }

    /**
     * Creates a new Bill object based on provided client and product IDs, and the specified quantity and total price.
     * This method fetches client and product details using their respective BLL classes to populate the new Bill.
     *
     * @param clientId The client ID for which the bill is created.
     * @param productId The product ID being billed.
     * @param quantity The quantity of the product being billed.
     * @param totalPrice The total price of the bill.
     * @return The newly created Bill object populated with client and product names, and the specified quantity and price.
     */
    public Bill createBill(Integer clientId, Integer productId, Integer quantity, Integer totalPrice) {
        String nameClient = clientBLL.abstractDAO.findById(clientId).getFirstName() + " " + clientBLL.abstractDAO.findById(clientId).getLastName();
        String nameProduct = productBLL.abstractDAO.findById(productId).getProductName();
        return new Bill(nameClient, nameProduct, quantity, totalPrice);
    }
}
