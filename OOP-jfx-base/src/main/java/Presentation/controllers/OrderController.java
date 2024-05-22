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

/**
 * Controller class for managing Order operations in the GUI.
 * This class provides functionalities to interact with order data, such as creating, viewing, and editing orders.
 */
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


    /**
     * Constructs an OrderController with initialized business logic layers for client and product handling.
     * This constructor is responsible for initializing the controller with necessary business logic layers,
     * ensuring that the necessary data manipulations can be performed on the client and product data.
     */
    public OrderController() {
        this.abstractBll = new OrderBLL();

    }

    /**
     * Initializes and prepares the UI components and bindings necessary for order management.
     * This method sets up table columns and data bindings for order display.
     */
    @FXML
    public void initialize() {
        super.initialize();
        clientComboBox.setItems(FXCollections.observableArrayList(clientBLL.getElements()));
        productComboBox.setItems(FXCollections.observableArrayList(productBLL.getElements()));

    }

    /**
     * Sets up the table columns with specific properties to display and allows for editing order details.
     * Configures each column to bind to properties of the Order model and allows for editing directly in the table.
     */
    @Override
    protected void setupTableColumns() {
        idColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());
        clientIdColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getClientId()).asObject());
        productIdColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getProductId()).asObject());
        productQuantityColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getProductQuantity()).asObject());
    }

    /**
     * Retrieves the TableColumn that is editable in the UI, specifically focusing on the product quantity column.
     * @return The editable TableColumn, which is the product quantity column.
     */
    @Override
    protected TableColumn<Order, ?> getEditableColumn() {
        return null; // Assuming no columns are editable for simplicity
    }

    /**
     * Checks if the product quantity input field is empty.
     * @return true if the field is empty, otherwise false.
     */
    @Override
    protected boolean areInputsEmpty() {
        return clientComboBox.getSelectionModel().isEmpty() ||
                productComboBox.getSelectionModel().isEmpty() ||
                productQuantityField.getText().isEmpty();
    }

    /**
     * Creates an Order item from user inputs, typically when adding a new Order from the UI.
     * @return a new instance of Order, filled with values from input fields.
     */
    @Override
    protected Order createItemFromInputs() {
        Client selectedClient = clientComboBox.getSelectionModel().getSelectedItem();
        Product selectedProduct = productComboBox.getSelectionModel().getSelectedItem();
        int productQuantity = Integer.parseInt(productQuantityField.getText());
        return new Order(selectedClient.getId(), selectedProduct.getId(), productQuantity);
    }

    /**
     * Clears the product quantity text field related to Order input in the UI.
     */
    @Override
    protected void clearTextFields() {
        clientComboBox.getSelectionModel().clearSelection();
        productComboBox.getSelectionModel().clearSelection();
        productQuantityField.clear();
    }


}
