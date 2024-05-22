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

/**
 * Controller class for managing the Product entities in a JavaFX table view.
 * This class handles the setup of table columns, and input validation, and allows editing of product details directly in the table.
 */
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

    /**
     * Constructs a ProductsController with initialized business logic layers for product handling.
     * This constructor is responsible for initializing the controller with necessary business logic layers,
     * ensuring that the necessary data manipulations can be performed on the product data.
     */
    public ProductsController() {
        this.abstractBll = new ProductBLL();
    }

    /**
     * Sets up the table columns with specific properties to display and allows for editing product details.
     * Configures each column to bind to properties of the Product model and allows for editing directly in the table.
     */
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

    /**
     * Retrieves the TableColumn that is editable in the UI, specifically focusing on the product name column.
     * @return The editable TableColumn, which is the product name column.
     */
    @Override
    protected TableColumn<Product, ?> getEditableColumn() {
        return productNameColumn;
    }

    /**
     * Checks if any of the input fields for new Product entries are empty.
     * @return true if any field is empty, otherwise false.
     */
    @Override
    protected boolean areInputsEmpty() {
        return validators.areTextFieldsEmpty(productNameInput.getText(), priceInput.getText(), quantityInput.getText());
    }

    /**
     * Creates a Product item from input fields. Typically invoked when adding a new Product from the UI.
     * @return a new instance of Product, filled with values from input fields.
     */
    @Override
    protected Product createItemFromInputs() {

        return new Product(productNameInput.getText(), Integer.parseInt(priceInput.getText()), Integer.parseInt(quantityInput.getText()));
    }

    /**
     * Clears all text fields related to Product input in the UI.
     */
    @Override
    protected void clearTextFields() {
        productNameInput.clear();
        priceInput.clear();
        quantityInput.clear();
    }

    /**
     * Updates a product's details in the business logic layer.
     * @param product The product to update.
     */
    private void updateItem(Product product) {
        abstractBll.updateElement(product);
    }

    /**
     * Deletes a product.
     * @param selectedItem The product to delete.
     */
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
