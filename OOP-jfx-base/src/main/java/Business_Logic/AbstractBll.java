package Business_Logic;

import Data_Access.AbstractDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.lang.reflect.ParameterizedType;
import java.util.List;

public abstract class AbstractBll<T> {
    public ObservableList<T> elements;
    private final Class<T> type;

    public AbstractDAO<T> abstractDAO;

    public ObservableList<T> getElements() {
        return elements;
    }


    @SuppressWarnings("unchecked")
    public AbstractBll() {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        abstractDAO = new AbstractDAO<>(type);
        elements = FXCollections.observableArrayList(abstractDAO.findAll());
    }

    public void addElement(T t) {
        abstractDAO.insert(t);
        T newElement = abstractDAO.findById(getGeneratedId()); // Fetch the new element with the generated ID
        elements.add(newElement); // Add the new element to the existing observable list
    }

    public void deleteElement(T t) {
        abstractDAO.delete(t);
        elements.remove(t);
    }

    public void updateElement(T t) {
        abstractDAO.update(t);
    }

    // Abstract method for validating elements
    public abstract boolean isValidElement(T t);

    public  int getGeneratedId(){
        return abstractDAO.getLastInsertId();
    }
}
