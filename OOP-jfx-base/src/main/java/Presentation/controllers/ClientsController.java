package Presentation.controllers;

import Business_Logic.ClientBLL;
import Business_Logic.Validators.validators;
import Model.Client;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldTableCell;

/**
 * Controller class for managing the Client entities in a JavaFX table view.
 * This class handles the setup of table columns, and input validation, and allows editing of client details directly in the table.
 */
public class ClientsController extends GenericController<Client> {
    @FXML
    protected TableColumn<Client, String> firstNameColumn;
    @FXML
    protected TableColumn<Client, String> lastNameColumn;
    @FXML
    protected TableColumn<Client, String> emailColumn;
    @FXML
    private TextField firstNameInput, lastNameInput, emailInput;

    /**
     * Initializes the controller with a specific business logic layer handling for clients.
     */
    public ClientsController() {
        this.abstractBll = new ClientBLL();
    }

    /**
     * Configures the table columns with the specific properties to display and allows for editing client details.
     * Utilizes JavaFX bindings to connect entity properties directly to table columns and adds editing capabilities.
     */
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

    /**
     * Returns the TableColumn that is editable in the UI.
     * @return The editable TableColumn, which is the first name column.
     */
    @Override
    protected TableColumn<Client, ?> getEditableColumn() {
        return firstNameColumn;
    }

    /**
     * Checks if any of the input fields for new Client entries are empty.
     * @return true if any field is empty, otherwise false.
     */
    @Override
    protected boolean areInputsEmpty() {
        return validators.areTextFieldsEmpty(firstNameInput.getText(), lastNameInput.getText(), emailInput.getText());
    }

    /**
     * Creates a Client item from input fields. Typically invoked when adding a new Client from the UI.
     * @return a new instance of Client, filled with values from input fields.
     */
    @Override
    protected Client createItemFromInputs() {
        return new Client(firstNameInput.getText(), lastNameInput.getText(), emailInput.getText());
    }

    /**
     * Clears all text fields related to Client input in the UI.
     */
    @Override
    protected void clearTextFields() {
        firstNameInput.clear();
        lastNameInput.clear();
        emailInput.clear();
    }

    /**
     * Updates a client's details in the business logic layer.
     * @param client The client to update.
     */
    private void updateItem(Client client) {
        abstractBll.updateElement(client);
    }
}
