package Presentation.controllers;

import Business_Logic.OrderBLL;
import Business_Logic.ProductBLL;
import Business_Logic.Validators.validators;
import Model.Order;
import javafx.fxml.FXML;
import Model.Product;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.IntegerStringConverter;

import java.util.ArrayList;
import java.util.List;

public class ProductsController extends GenericController<Product> {
    @FXML
    protected TableColumn<Product, String> productNameColumn;
    @FXML
    protected TableColumn<Product, Integer> priceColumn;
    @FXML
    protected TableColumn<Product, Integer> quantityColumn;
    @FXML
    private TextField productNameInput, priceInput, quantityInput;

    OrderBLL orderBLL=new OrderBLL();
    public ProductsController() {
        this.abstractBll = new ProductBLL();
    }

    @Override
    protected void setupTableColumns() {
        idColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());

        productNameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getProductName()));
        productNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        productNameColumn.setOnEditCommit(event -> {
            Product product = event.getRowValue();
            product.setProductName(event.getNewValue());
            updateItem(product);
        });

        priceColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getPrice()).asObject());
        priceColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        priceColumn.setOnEditCommit(event -> {
            Product product = event.getRowValue();
            product.setPrice(event.getNewValue());
            updateItem(product);
        });

        quantityColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getQuantity()).asObject());
        quantityColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        quantityColumn.setOnEditCommit(event -> {
            Product product = event.getRowValue();
            product.setQuantity(event.getNewValue());
            updateItem(product);
        });
    }

    @Override
    protected TableColumn<Product, ?> getEditableColumn() {
        return productNameColumn;
    }

    @Override
    protected boolean areInputsEmpty() {
        return validators.areTextFieldsEmpty(productNameInput.getText(), priceInput.getText(), quantityInput.getText());
    }

    @Override
    protected Product createItemFromInputs() {

        return new Product(productNameInput.getText(), Integer.parseInt(priceInput.getText()), Integer.parseInt(quantityInput.getText()));
    }

    @Override
    protected void clearTextFields() {
        productNameInput.clear();
        priceInput.clear();
        quantityInput.clear();
    }

    private void updateItem(Product product) {
        abstractBll.updateElement(product);
    }

    @Override
    public void deleteItem(Product selectedItem) {
        try {
            List<Order> orders = orderBLL.getElements();
            if (orders != null) {
                for (Order order : new ArrayList<>(orders)) { // Avoid concurrent modification
                    if (order.getProductId().equals(selectedItem.getId())) {
                        orderBLL.deleteElement(order);
                    }
                }
            } else {
                System.err.println("Ordher list is null, skipping order deletion.");
            }
            super.deleteItem(selectedItem);
        }catch (Exception e) {
            e.printStackTrace(); // Log the exception stack trace
            System.err.println("An error occurred while deleting the item: " + e.getMessage());
        }
    }
}
