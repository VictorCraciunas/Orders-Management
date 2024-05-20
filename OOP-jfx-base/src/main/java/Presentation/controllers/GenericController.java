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

public abstract class GenericController<T> extends SceneController {
    @FXML
    protected TableView<T> tableView;

    @FXML
    protected TableColumn<T, Integer> idColumn;

    AbstractBll<T> abstractBll;

    protected ObservableList<T> items;
    @FXML
    public void initialize() {
        setupTableColumns();
        items = abstractBll.getElements();
        tableView.setItems(items);
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

    protected abstract void setupTableColumns();

    protected abstract TableColumn<T, ?> getEditableColumn();


    @FXML
    public void handleAddItem() {
        if (!areInputsEmpty()) {
            T newClient = createItemFromInputs();
            if (!abstractBll.isValidElement(newClient)) {
                showAlert(Alert.AlertType.ERROR, "Invalid client data");
                return;
            }
            addItem(newClient);
            clearTextFields();
        } else {
            showAlert(Alert.AlertType.ERROR, "Empty TextFields for inserting an item");
        }
    }

    protected abstract boolean areInputsEmpty();

    protected abstract T createItemFromInputs();

    protected abstract void clearTextFields();

    public void addItem(T newItem) {
        abstractBll.addElement(newItem);
    }

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

    public void deleteItem(T selectedItem) {
        abstractBll.deleteElement(selectedItem);
    }

    public void showAlert(Alert.AlertType alertType, String message) {
        Alert alert = new Alert(alertType, message);
        alert.show();
    }
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
}
