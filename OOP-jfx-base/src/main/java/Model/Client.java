package Model;

/**
 * Represents a client with personal information such as first name, last name, and email.
 */
public class Client {
    public Integer id;
    public String firstName;
    public String lastName;
    public String email;


    /**
     * Default constructor for creating an empty Client.
     */
    public Client() {

    }

    /**
     * Constructs a Client with specified first name, last name, and email.
     * This constructor is typically used when creating a new client before persistence.
     * @param firstName The client's first name.
     * @param lastName The client's last name.
     * @param email The client's email address.
     */
    public Client(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }


    /**
     * Constructs a Client with an ID and personal details.
     * This constructor is typically used when retrieving a client's information from the database.
     * @param id The unique identifier for the client.
     * @param firstName The client's first name.
     * @param lastName The client's last name.
     * @param email The client's email address.
     */
    public Client(Integer id, String firstName, String lastName, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    /**
     * Returns a string representation of the client, which is a combination of first and last names.
     * @return A string consisting of the client's first and last names.
     */
    @Override
    public String toString() {
        return  firstName + " " + lastName ;
    }
}
