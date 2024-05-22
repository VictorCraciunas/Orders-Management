package Presentation.controllers;

import Business_Logic.BillBLL;
import Model.Bill;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;

/**
 * Controller class for managing the Bill entities in a JavaFX table view.
 * This class handles the setup of table columns and interaction logic for the Bill entities.
 */
public class BillController extends GenericController<Bill> {
    @FXML
    protected TableColumn<Bill, String> nameClientColumn;
    @FXML
    protected TableColumn<Bill, String> nameProductColumn;
    @FXML
    protected TableColumn<Bill, Integer> quantityColumn;

    @FXML
    protected TableColumn<Bill, Integer> totalPriceColumn;

    /**
     * Initializes the controller with a specific business logic layer handling for bills.
     */
    public BillController() {
        this.abstractBll = new BillBLL();
    }

    /**
     * Configures the table columns with the specific properties to display for each column.
     * Utilizes JavaFX bindings to connect entity properties directly to table columns.
     */
    @Override
    protected void setupTableColumns() {
        idColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());
        nameClientColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNameClient()));
        nameProductColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNameProduct()));
        quantityColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getQuantity()).asObject());
        totalPriceColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getTotalPrice()).asObject());
    }

    /**
     * Returns the TableColumn that is editable in the UI. In this case it return null because we don't want the user to edit the table.
     * @return The editable TableColumn, or null if no column is editable.
     */
    @Override
    protected TableColumn<Bill, ?> getEditableColumn() {
        return null;
    }

    /**
     * Checks if any of the input fields for new Bill entries are empty. But we don't have any inputs here
     * @return true if any field is empty, otherwise false.
     */
    @Override
    protected boolean areInputsEmpty() {
        return false;
    }

    /**
     * Creates a Bill item from input fields. Typically invoked when adding a new Bill from the UI.
     * @return a new instance of Bill, filled with values from input fields.
     */
    @Override
    protected Bill createItemFromInputs() {
        return new Bill();
    }

    /**
     * Clears all text fields related to Bill input in the UI.
     */
    @Override
    protected void clearTextFields() {

    }
}
