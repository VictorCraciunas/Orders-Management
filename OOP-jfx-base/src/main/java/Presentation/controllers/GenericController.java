package Presentation.controllers;

import Business_Logic.AbstractBll;
import Model.Client;
import com.jfxbase.oopjfxbase.utils.ApplicationHandler;
import com.jfxbase.oopjfxbase.utils.SceneController;

import com.jfxbase.oopjfxbase.utils.enums.SCENE_IDENTIFIER;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

/**
 * Abstract base class for all controllers managing various entities in the application.
 * Provides common functionalities such as setting up table views, adding, deleting, and editing items.
 *
 * @param <T> the type parameter that represents the entity type managed by this controller.
 */
public abstract class GenericController<T> extends SceneController {
    @FXML
    protected TableView<T> tableView;

    @FXML
    protected TableColumn<T, Integer> idColumn;

    AbstractBll<T> abstractBll;

    /**
     * Initializes the controller, setting up the table columns and binding items to the table view.
     */
    @FXML
    public void initialize() {
        setupTableColumns();
        tableView.setItems(abstractBll.getElements());
        tableView.setEditable(true);

        tableView.setRowFactory(tv -> {
            TableRow<T> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty() && event.getClickCount() == 2) {
                    tableView.edit(row.getIndex(), getEditableColumn());
                }
            });
            return row;
        });
    }

    /**
     * Abstract method to set up the columns in the table view. Must be implemented by subclasses.
     */
    protected abstract void setupTableColumns();

    /**
     * Returns the TableColumn that is editable. This method should be overridden to specify the editable column.
     * @return the TableColumn that is editable.
     */
    protected abstract TableColumn<T, ?> getEditableColumn();

    /**
     * Handles the action of adding a new item. Validates inputs and adds the item if valid.
     */
    @FXML
    public void handleAddItem() {
        if (!areInputsEmpty()) {
            T newItem = createItemFromInputs();
            if (!abstractBll.isValidElement(newItem)) {
                showAlert(Alert.AlertType.ERROR, "Invalid data");
                return;
            }
            addItem(newItem);
            clearTextFields();
        } else {
            showAlert(Alert.AlertType.ERROR, "Empty TextFields for inserting an item");
        }
    }

    /**
     * Abstract method to check if the input fields are empty. Must be implemented by subclasses.
     * @return true if any input is empty, otherwise false.
     */
    protected abstract boolean areInputsEmpty();

    /**
     * Abstract method to create an item from input fields. Must be implemented by subclasses.
     * @return a newly created item of type T.
     */
    protected abstract T createItemFromInputs();

    /**
     * Abstract method to clear text fields in the UI. Must be implemented by subclasses.
     */
    protected abstract void clearTextFields();

    /**
     * Adds a new item to the business logic layer.
     * @param newItem the new item to add.
     */
    public void addItem(T newItem) {
        abstractBll.addElement(newItem);
    }

    /**
     * Handles the action of deleting a selected item.
     */
    @FXML
    public void handleDeleteItem() {
        T selectedItem = tableView.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this item?", ButtonType.YES, ButtonType.NO);
            confirmAlert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.YES) {
                    deleteItem(selectedItem);
                }
            });
        } else {
            showAlert(Alert.AlertType.ERROR, "No item selected");
        }
    }

    /**
     * Deletes the selected item from the business logic layer.
     * @param selectedItem the item to delete.
     */
    public void deleteItem(T selectedItem) {
        abstractBll.deleteElement(selectedItem);
    }

    /**
     * Displays an alert to the user.
     * @param alertType the type of alert.
     * @param message the message to display in the alert.
     */
    public void showAlert(Alert.AlertType alertType, String message) {
        Alert alert = new Alert(alertType, message);
        alert.show();
    }

    // Navigation methods with @FXML annotation showing how to navigate between views in the application.
    @FXML
    protected void goToClientsView() {
        ApplicationHandler._instance.navigateToClientsView();
    }

    @FXML
    protected void goToProductsView() {
        ApplicationHandler._instance.navigateToProductsView();
    }
    @FXML
    protected void goToOrdersView() {
        ApplicationHandler._instance.navigateToOrdersView();
    }
    @FXML
    protected void goToBillsView() {
        ApplicationHandler._instance.navigateToBillsView();
    }
}
