package Business_Logic;

import Data_Access.AbstractDAO;

import java.lang.reflect.ParameterizedType;
import java.util.List;

public class AbstractBll <T>{
    public List<T> elements;
    private final Class<T> type;


    public List<T> getElements() {
        return elements;
    }

    public AbstractDAO<T> abstractDAO;

    @SuppressWarnings("unchecked")
    public AbstractBll() {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        abstractDAO=new AbstractDAO<>(type);
        elements=abstractDAO.findAll();
    }

    public void addElement(T t){
        abstractDAO.insert(t);
        elements.add(t);
    }

    public void deleteElement(T t){
        abstractDAO.delete(t);
        elements.remove(t);
    }

    public void updateElement(T t){
        abstractDAO.update(t);
    }

}
