package Data_Access;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import connection.ConnectionFactory;

public class AbstractDAO<T> {
    protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());

    private final Class<T> type;

    public AbstractDAO(Class<T> type) {
        this.type = type;
    }

    private String createSelectQuery(String field) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM ");
        sb.append("\"" + type.getSimpleName().toLowerCase() + "\"");
        sb.append(" WHERE " + field + " =?");
        return sb.toString();
    }

    public List<T> findAll() {
        List<T> list = new ArrayList<>();
        String sql = "SELECT * FROM " + "\"" + type.getSimpleName().toLowerCase() +"\"";
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            list = createObjects(resultSet);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return list;
    }

    public T findById(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery("id");
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            return createObjects(resultSet).get(0);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    private List<T> createObjects(ResultSet resultSet) {
        List<T> list = new ArrayList<T>();
        Constructor[] ctors = type.getDeclaredConstructors();
        Constructor ctor = null;
        for (int i = 0; i < ctors.length; i++) {
            ctor = ctors[i];
            if (ctor.getGenericParameterTypes().length == 0)
                break;
        }
        try {
            while (resultSet.next()) {
                ctor.setAccessible(true);
                T instance = (T) ctor.newInstance();
                for (Field field : type.getDeclaredFields()) {
                    String fieldName = field.getName();
                    Object value = resultSet.getObject(fieldName);
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, type);
                    Method method = propertyDescriptor.getWriteMethod();
                    method.invoke(instance, value);
                }
                list.add(instance);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean insert(T t) {
        Connection connection = null;
        PreparedStatement statement = null;
        boolean result = false;

        try {
            connection = ConnectionFactory.getConnection();
            String tableName = type.getSimpleName().toLowerCase();

            Field[] fields = type.getDeclaredFields();
            StringBuilder fieldNames = new StringBuilder(); // (the fields/collumns of the table)
            StringBuilder placeholders = new StringBuilder(); // the values for the fields (in query this is ? ? )

            for (Field field : fields) {
                field.setAccessible(true);
                if (field.getName().equals("id")) {
                    continue; // we need to skip the collumn id
                }
                fieldNames.append("\"").append(field.getName()).append("\"").append(","); // we need to put the field name between \" \" because the PostgreSQL will convert the field name with lowecase automatically
                placeholders.append("?,");
            }

            // Remove the last comma
            fieldNames.setLength(fieldNames.length() - 1);
            placeholders.setLength(placeholders.length() - 1);

            String query = "INSERT INTO " + "\"" + tableName + "\""  + " (" + fieldNames + ") VALUES (" + placeholders + ")";
            statement = connection.prepareStatement(query);

            // we replace the ? with the value of each field
            int index = 1;
            for (Field field : fields) {
                field.setAccessible(true);
                if (field.getName().equals("id")) {
                    continue; // we need to skip the collumn id
                }
                statement.setObject(index++, field.get(t));
            }

            result = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Failed to insert object: " + e.getMessage(), e);
        } catch (IllegalAccessException e) {
            LOGGER.log(Level.SEVERE, "Illegal access: " + e.getMessage(), e);
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }

        return result;
    }

    public boolean update(T t) {
        Connection connection = null;
        PreparedStatement statement = null;
        boolean result = false;

        try {
            connection = ConnectionFactory.getConnection();
            String tableName = type.getSimpleName().toLowerCase();

            Field[] fields = type.getDeclaredFields();
            StringBuilder fieldNames = new StringBuilder(); // (the fields/collumns of the table)

            for (Field field : fields) {
                field.setAccessible(true);
                if (field.getName().equals("id")) {
                    continue; // we need to skip the collumn id
                }
                fieldNames.append("\"").append(field.getName()).append("\"").append(" = ?,"); // we need to put the field name between \ because the PostgreSQL will convert the field name with lowecase automatically
            }

            // Remove the last comma
            fieldNames.setLength(fieldNames.length() - 1);

            String query = "UPDATE " + "\"" + tableName + "\""  + " Set " + fieldNames + " WHERE id = ?";
            statement = connection.prepareStatement(query);

            // we replace the ? with the value of each field
            int index = 1;
            for (Field field : fields) {
                field.setAccessible(true);
                if (field.getName().equals("id")) {
                    continue; // we need to skip the collumn id
                }
                statement.setObject(index++, field.get(t));
            }

            //we put the id
            for (Field field : fields) {
                field.setAccessible(true);
                if (field.getName().equals("id")) {
                    statement.setObject(index, field.get(t));
                    break;
                }
            }

            result = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Failed to update object: " + e.getMessage(), e);
        } catch (IllegalAccessException e) {
            LOGGER.log(Level.SEVERE, "Illegal access: " + e.getMessage(), e);
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }

        return result;
    }

    public boolean delete(T t) {
        Connection connection = null;
        PreparedStatement statement = null;
        boolean result = false;
        String sql = "DELETE FROM " + "\"" + type.getSimpleName().toLowerCase()+ "\"" + " WHERE id = ?";
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);

            for (Field field : type.getDeclaredFields()) {
                field.setAccessible(true);
                if (field.getName().equals("id")) {
                    statement.setObject(1, field.get(t));
                    break;
                }
            }

            result = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Failed to delete object: " + e.getMessage(), e);
        } catch (IllegalAccessException e) {
            LOGGER.log(Level.SEVERE, "Illegal access: " + e.getMessage(), e);
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return result;
    }
}
