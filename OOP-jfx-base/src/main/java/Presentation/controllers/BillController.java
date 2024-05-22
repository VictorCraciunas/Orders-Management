package Presentation.controllers;

import Business_Logic.BillBLL;
import Model.Bill;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;

public class BillController extends GenericController<Bill> {
    @FXML
    protected TableColumn<Bill, String> nameClientColumn;
    @FXML
    protected TableColumn<Bill, String> nameProductColumn;
    @FXML
    protected TableColumn<Bill, Integer> quantityColumn;

    @FXML
    protected TableColumn<Bill, Integer> totalPriceColumn;

    public BillController() {
        this.abstractBll = new BillBLL();
    }

    @Override
    protected void setupTableColumns() {
        idColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());
        nameClientColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNameClient()));
        nameProductColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNameProduct()));
        quantityColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getQuantity()).asObject());
        totalPriceColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getTotalPrice()).asObject());
    }

    @Override
    protected TableColumn<Bill, ?> getEditableColumn() {
        return null;
    }

    @Override
    protected boolean areInputsEmpty() {
        return false;
    }

    @Override
    protected Bill createItemFromInputs() {
        return new Bill();
    }

    @Override
    protected void clearTextFields() {

    }
}
