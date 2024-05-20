package Presentation.controllers;

import Business_Logic.AbstractBll;
import Model.Client;
import com.jfxbase.oopjfxbase.utils.SceneController;

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

    @FXML
    public void initialize() {
        setupTableColumns();
        tableView.setItems(loadData());
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

    private ObservableList<T> loadData() {
        ObservableList<T> items = FXCollections.observableArrayList();
        items.addAll(abstractBll.getElements());
        return items;
    }

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
        tableView.setItems(loadData());
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
        tableView.setItems(loadData());
    }

    public void showAlert(Alert.AlertType alertType, String message) {
        Alert alert = new Alert(alertType, message);
        alert.show();
    }
}
