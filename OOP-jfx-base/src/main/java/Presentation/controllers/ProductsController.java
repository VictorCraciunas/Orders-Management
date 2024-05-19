package Presentation.controllers;

import Business_Logic.ProductBLL;
import javafx.fxml.FXML;
import Model.Product;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.IntegerStringConverter;

public class ProductsController extends GenericController<Product> {
    @FXML
    protected TableColumn<Product, String> productNameColumn;
    @FXML
    protected TableColumn<Product, Integer> priceColumn;
    @FXML
    protected TableColumn<Product, Integer> quantityColumn;
    @FXML
    private TextField productNameInput, priceInput, quantityInput;

    public ProductsController() {
        this.abstractBll=new ProductBLL();
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
    protected boolean areTextFieldsEmpty() {
        return productNameInput.getText().isEmpty() || priceInput.getText().isEmpty() || quantityInput.getText().isEmpty();
    }

    @Override
    protected Product createItemFromInputs() {

        return new Product(productNameInput.getText(),Integer.parseInt(priceInput.getText()),Integer.parseInt(quantityInput.getText()));
    }

    @Override
    protected void clearTextFields() {

    }

    private void updateItem(Product product) {
        abstractBll.updateElement(product);
    }
}
