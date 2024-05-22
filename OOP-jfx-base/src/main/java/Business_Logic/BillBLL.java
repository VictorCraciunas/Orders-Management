package Business_Logic;

import Model.Bill;

public class BillBLL extends AbstractBll<Bill>{
    ClientBLL clientBLL=new ClientBLL();
    ProductBLL productBLL=new ProductBLL();
    @Override
    public boolean isValidElement(Bill bill) {
        return true;
    }

    public Bill createBill(Integer clientId, Integer productId,Integer quantity, Integer totalPrice){
        String nameClient=clientBLL.abstractDAO.findById(clientId).getFirstName() + " " +clientBLL.abstractDAO.findById(clientId).getLastName();
        String nameProduct=productBLL.abstractDAO.findById(productId).getProductName();
        return new Bill(nameClient,nameProduct,quantity,totalPrice);
    }


}
