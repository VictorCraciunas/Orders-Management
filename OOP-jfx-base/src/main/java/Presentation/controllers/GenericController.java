package Presentation.controllers;

import Business_Logic.AbstractBll;
import com.jfxbase.oopjfxbase.utils.SceneController;

import Data_Access.AbstractDAO;
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
    private void handleAddItem() {
        if (!areTextFieldsEmpty()) {
            T newItem = createItemFromInputs();
            addItem(newItem);
            clearTextFields();
        } else {
            showAlert(Alert.AlertType.ERROR, "Empty TextFields for inserting an item");
        }
    }

    protected abstract boolean areTextFieldsEmpty();
    protected abstract T createItemFromInputs();
    protected abstract void clearTextFields();

    private void addItem(T newItem) {
        abstractBll.addElement(newItem);
        tableView.setItems(loadData());
    }

    @FXML
    private void handleDeleteItem() {
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

    private void deleteItem(T selectedItem) {
        abstractBll.deleteElement(selectedItem);
        tableView.setItems(loadData());
    }

    private void showAlert(Alert.AlertType alertType, String message) {
        Alert alert = new Alert(alertType, message);
        alert.show();
    }
}
