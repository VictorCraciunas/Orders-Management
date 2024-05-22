package Presentation.controllers;

import Business_Logic.ClientBLL;
import Business_Logic.OrderBLL;
import Business_Logic.ProductBLL;
import Model.Client;
import Model.Order;
import Model.Product;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;

public class OrderController extends GenericController<Order> {
    @FXML
    private TableColumn<Order, Integer> clientIdColumn;
    @FXML
    private TableColumn<Order, Integer> productIdColumn;
    @FXML
    private TableColumn<Order, Integer> productQuantityColumn;
    @FXML
    private TextField productQuantityField;
    @FXML
    private ComboBox<Client> clientComboBox;
    @FXML
    private ComboBox<Product> productComboBox;

    ClientBLL clientBLL=new ClientBLL();
    ProductBLL productBLL=new ProductBLL();



    public OrderController() {
        this.abstractBll = new OrderBLL();

    }

    @FXML
    public void initialize() {
        super.initialize();
        clientComboBox.setItems(FXCollections.observableArrayList(clientBLL.getElements()));
        productComboBox.setItems(FXCollections.observableArrayList(productBLL.getElements()));

    }

    @Override
    protected void setupTableColumns() {
        idColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());
        clientIdColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getClientId()).asObject());
        productIdColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getProductId()).asObject());
        productQuantityColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getProductQuantity()).asObject());
    }

    @Override
    protected TableColumn<Order, ?> getEditableColumn() {
        return null; // Assuming no columns are editable for simplicity
    }

    @Override
    protected boolean areInputsEmpty() {
        return clientComboBox.getSelectionModel().isEmpty() ||
                productComboBox.getSelectionModel().isEmpty() ||
                productQuantityField.getText().isEmpty();
    }

    @Override
    protected Order createItemFromInputs() {
        Client selectedClient = clientComboBox.getSelectionModel().getSelectedItem();
        Product selectedProduct = productComboBox.getSelectionModel().getSelectedItem();
        int productQuantity = Integer.parseInt(productQuantityField.getText());
        return new Order(selectedClient.getId(), selectedProduct.getId(), productQuantity);
    }

    @Override
    protected void clearTextFields() {
        clientComboBox.getSelectionModel().clearSelection();
        productComboBox.getSelectionModel().clearSelection();
        productQuantityField.clear();
    }


}
