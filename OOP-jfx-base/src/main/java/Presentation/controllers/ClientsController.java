package Presentation.controllers;

import Data_Access.ClientDAO;
import Model.Client;
import com.jfxbase.oopjfxbase.utils.SceneController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.cell.TextFieldTableCell;

public class ClientsController extends SceneController {
    @FXML
    private TableView<Client> tableView;

    @FXML
    private TableColumn<Client, Integer> idColumn;

    @FXML
    private TableColumn<Client, String> firstNameColumn;

    @FXML
    private TableColumn<Client, String> lastNameColumn;

    @FXML
    private TableColumn<Client, String> emailColumn;
    @FXML
    private TextField firstNameInput, lastNameInput, emailInput;

    ClientDAO clientDAO = new ClientDAO();

    @FXML
    public void initialize() {
        // Set custom cell value factories
        idColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());
        firstNameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFirstName()));
        firstNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        firstNameColumn.setOnEditCommit(event -> {
            Client client = event.getRowValue();
            client.setFirstName(event.getNewValue());
            updateClient(client);
        });
        lastNameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getLastName()));
        lastNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        lastNameColumn.setOnEditCommit(event -> {
            Client client = event.getRowValue();
            client.setLastName(event.getNewValue());
            updateClient(client);
        });
        emailColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmail()));
        emailColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        emailColumn.setOnEditCommit(event -> {
            Client client = event.getRowValue();
            client.setEmail(event.getNewValue());
            updateClient(client);
        });

        // Optionally, load and set the data model for the TableView
        tableView.setItems(loadClientData());
        tableView.setEditable(true);

        tableView.setRowFactory(tv -> {
            TableRow<Client> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty() && event.getClickCount() == 2) {
                    tableView.edit(row.getIndex(), firstNameColumn);
                }
            });
            return row;
        });
    }

    private ObservableList<Client> loadClientData() {
        ObservableList<Client> clients = FXCollections.observableArrayList();
        clients.addAll(clientDAO.findAll());
        return clients;
    }

    @FXML
    private void handleAddClient() {
        String firstName = firstNameInput.getText();
        String lastName = lastNameInput.getText();
        String email = emailInput.getText();

        if(!areTextFieldsEmpty(firstName, lastName, email)){
            // Create a new Client object
            Client newClient = new Client(firstName, lastName, email);

            // Clear the input fields after adding
            firstNameInput.clear();
            lastNameInput.clear();
            emailInput.clear();


            addClient(newClient);
        }
        else {
            Alert confirmAlert = new Alert(Alert.AlertType.ERROR, "Empty TextFields for inserting a client");
            confirmAlert.show();
        }
    }

    private boolean areTextFieldsEmpty(String firstName, String lastName, String email){
        if (!firstName.isEmpty() && !lastName.isEmpty() && !email.isEmpty()){
            return false;
        }
        else{
            return true;
        }
    }


    private void addClient(Client newClient){
        clientDAO.insert(newClient);
        tableView.setItems(loadClientData());
    }

    private void updateClient(Client client) {
        clientDAO.update(client);  // Assuming there's an update method in your DAO
    }


    @FXML
    private void handleDeleteClient() {
        Client selectedClient = tableView.getSelectionModel().getSelectedItem();

        if (selectedClient != null) {
            Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this client?", ButtonType.YES, ButtonType.NO);
            confirmAlert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.YES) {
                    deleteClient(selectedClient);
                }
            });
        } else {
            Alert confirmAlert = new Alert(Alert.AlertType.ERROR, "No client selected");
            confirmAlert.show();
        }
    }

    private void deleteClient(Client selectedClient){
        tableView.getItems().remove(selectedClient);
        clientDAO.delete(selectedClient);
        tableView.refresh();

    }
}
