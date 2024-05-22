package Business_Logic;

import Data_Access.AbstractDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.lang.reflect.ParameterizedType;

/**
 * This abstract class provides a base for business logic layer functionality
 * for managing database entities using an AbstractDAO.
 *
 * @param <T> The type of the entity being managed.
 */
public abstract class AbstractBll<T> {
    public ObservableList<T> elements;
    private final Class<T> type;

    public AbstractDAO<T> abstractDAO;

    /**
     * Initializes the DAO and fetches all elements from the database to populate the observable list.
     */
    @SuppressWarnings("unchecked")
    public AbstractBll() {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        abstractDAO = new AbstractDAO<>(type);
        elements = FXCollections.observableArrayList(abstractDAO.findAll());
    }

    /**
     * Fetches all elements currently managed by this BLL.
     *
     * @return An ObservableList containing all managed elements.
     */
    public ObservableList<T> getElements() {
        return elements;
    }

    /**
     * Adds a new element to the database and the managed list.
     *
     * @param t The element to be added.
     */
    public void addElement(T t) {
        abstractDAO.insert(t);
        T newElement = abstractDAO.findById(getGeneratedId());
        elements.add(newElement);
    }

    /**
     * Deletes an element from the database and the managed list.
     *
     * @param t The element to be deleted.
     */
    public void deleteElement(T t) {
        abstractDAO.delete(t);
        elements.remove(t);
    }

    /**
     * Updates an existing element in the database.
     *
     * @param t The element to be updated.
     */
    public void updateElement(T t) {
        abstractDAO.update(t);
    }

    /**
     * Validates an element.
     *
     * @param t The element to validate.
     * @return true if the element is valid, false otherwise.
     */
    public abstract boolean isValidElement(T t);

    /**
     * Retrieves the last generated ID from the DAO.
     *
     * @return The last generated ID as an integer.
     */
    public int getGeneratedId(){
        return abstractDAO.getLastInsertId();
    }
}
