package Business_Logic;

import Data_Access.AbstractDAO;

import java.lang.reflect.ParameterizedType;
import java.util.List;

public abstract class AbstractBll<T> {
    public List<T> elements;
    private final Class<T> type;

    public AbstractDAO<T> abstractDAO;

    public List<T> getElements() {
        return elements;
    }


    @SuppressWarnings("unchecked")
    public AbstractBll() {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        abstractDAO = new AbstractDAO<>(type);
        elements = abstractDAO.findAll();
    }

    public void addElement(T t) {
        abstractDAO.insert(t);
        elements = abstractDAO.findAll();
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

}
