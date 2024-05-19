package Presentation.controllers;

import Business_Logic.ClientBLL;
import Model.Client;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldTableCell;

public class ClientsController extends GenericController<Client> {
    @FXML
    protected TableColumn<Client, String> firstNameColumn;
    @FXML
    protected TableColumn<Client, String> lastNameColumn;
    @FXML
    protected TableColumn<Client, String> emailColumn;
    @FXML
    private TextField firstNameInput, lastNameInput, emailInput;

    public ClientsController() {
        this.abstractBll = new ClientBLL();
    }

    @Override
    protected void setupTableColumns() {
        idColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());
        firstNameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFirstName()));
        firstNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        firstNameColumn.setOnEditCommit(event -> {
            Client client = event.getRowValue();
            client.setFirstName(event.getNewValue());
            updateItem(client);
        });

        lastNameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getLastName()));
        lastNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        lastNameColumn.setOnEditCommit(event -> {
            Client client = event.getRowValue();
            client.setLastName(event.getNewValue());
            updateItem(client);
        });

        emailColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmail()));
        emailColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        emailColumn.setOnEditCommit(event -> {
            Client client = event.getRowValue();
            client.setEmail(event.getNewValue());
            updateItem(client);
        });
    }

    @Override
    protected TableColumn<Client, ?> getEditableColumn() {
        return firstNameColumn;
    }

    @Override
    protected boolean areTextFieldsEmpty() {
        return firstNameInput.getText().isEmpty() || lastNameInput.getText().isEmpty() || emailInput.getText().isEmpty();
    }

    @Override
    protected Client createItemFromInputs() {
        return new Client(firstNameInput.getText(), lastNameInput.getText(), emailInput.getText());
    }

    @Override
    protected void clearTextFields() {
        firstNameInput.clear();
        lastNameInput.clear();
        emailInput.clear();
    }

    private void updateItem(Client client) {
        abstractBll.updateElement(client);
    }
}
